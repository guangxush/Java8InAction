import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.*;
import java.util.function.Function;

import static java.util.Comparator.comparing;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class LearnLambda {
    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("Hello1");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello2");
            }
        };
        process(() -> System.out.println("Hello3"));

        // 方法引用
        List<String> str = Arrays.asList("a", "b", "A", "B");
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        str = Arrays.asList("a", "b", "A", "B");
        str.sort(String::compareToIgnoreCase);
        Function<String, Integer> stringToInteger = Integer::parseInt;
        BiPredicate<List<String>, String> contains = List::contains;


        //Project02 类型推断
        Comparator<Apple> c1 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        ToIntBiFunction<Apple, Apple> c2 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        BiFunction<Apple, Apple, Integer> c3 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        //有类型推断
        Comparator<Apple> c4 = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());

        //方法引用
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"),
                new Apple(120, "red"));
        inventory.sort(comparing(Apple::getWeight));

        //无参构造函数引用
        Supplier<Apple> c5 = Apple::new;
        Apple a5 = c5.get();
        //等价于
        c5 = () -> new Apple();
        a5 = c5.get();

        //有参构造函数引用
        Function<Integer, Apple> c6 = Apple::new;
        Apple a6 = c6.apply(110);
        //等价于
        c6 = (weight) -> new Apple(weight);
        a6 = c6.apply(110);

        //两个参数构造函数引用
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = FilterAppleOne.filterApples(inventory, "green", 0, true);
        apples = map(weights, Apple::new);
        BiFunction<Integer, String, Apple> c7 = Apple::new;
        Apple a7 = c7.apply(110, "green");
        //等价于
        c7 = (weight, color) -> new Apple(weight, color);
        a7 = c7.apply(110, "green");

        //1. 传递代码 AppleComparator
        inventory.sort(new AppleComparator());
        //2. 使用匿名类
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        //3. 使用Lambda表达式
        inventory.sort((Apple o1, Apple o2) -> o1.getWeight().compareTo(o2.getWeight()));
        inventory.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));


        //4. 使用方法引用
        Comparator<Apple> c = Comparator.comparing((Apple o) -> o.getWeight());
        inventory.sort(comparing(Apple::getWeight));

        //比较器复合
        //1.逆序
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        //2.比较器链
        inventory.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor)); //随便写一下

        //函数复合
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result1 = h.apply(1);

        h = f.compose(g);
        int result2 = h.apply(1);

        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
                = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);

        DoubleFunction<Double> f2 = x -> x + 10;
        integrate(f2, 3.0, 7.0);
    }

    public static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }

    public static void process(Runnable r) {
        r.run();
    }

    public Callable<String> fetch() {
        return () -> "Tricky example";
    }

    public static List<Apple> map(List<Integer> list,
                                  Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer e : list) {
            result.add(f.apply(e));
        }
        return result;
    }
}
