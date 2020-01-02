
import javafx.animation.Animation;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author: guangxush
 * @create: 2020/01/01
 */
public class LearnCompletableFuture {
    static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    //创建一个线程池，线程池中线程的数目为100和商店数目二者中较小的一个值
    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }
            });

    private Rate exchangeService = new Rate();

    public static void main(String[] args) {
        //采用顺序查询
        long start = System.nanoTime();
        System.out.println(findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        //采用并行流
        start = System.nanoTime();
        System.out.println(findPricesByParallel("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        //采用CompletableFuture发起异步请求
        start = System.nanoTime();
        System.out.println(findPricesByCompletableFuture("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        long finalStart = System.nanoTime();
        CompletableFuture[] futures = findPricesStream("myPhone27S")
                .map(f -> f.thenAccept(
                        s -> System.out.println(s + " (done in " +
                                ((System.nanoTime() - finalStart) / 1_000_000) + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in "
                + ((System.nanoTime() - finalStart) / 1_000_000) + " msecs");


    }

    public static List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product))).collect(toList());
    }

    public static List<String> findPricesByParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product))).collect(toList());
    }

    public static List<String> findPricesByCompletableFuture(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))))
                .collect(toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }

    /**
     * 增加折扣服务，1s延迟
     *
     * @param product
     * @return
     */
    public List<String> findPricesWithDiscount(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }

    /**
     * 构造同步和异步操作
     *
     * @param product
     * @return
     */
    public List<String> findPricesWithDiscountByCompletableFuture(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                // 以异步方式取得 每个shop中指定 产品的原始价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                //使用另一个异 步任务构造期 望的Future， 申请折扣 第二个结果依赖于第一个结果
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    public Future<Double> findPricesWithRate(Shop shop, String product) {
        Future<Double> futurePriceInUSD =
                CompletableFuture.supplyAsync(() -> shop.getPrice(product)).thenCombine(
                        CompletableFuture.supplyAsync(
                                () -> exchangeService.getRate(Rate.Money.EUR, Rate.Money.USD)),
                        (price, rate) -> Double.valueOf(price) * rate);
        return futurePriceInUSD;
    }

    public static Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse)).map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor)));
    }

}
