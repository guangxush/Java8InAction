import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;


/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class LearnStream {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<String> threeHighCaloricDishNames =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .map(Dish::getName).limit(3)
                        .collect(toList());
        System.out.println(threeHighCaloricDishNames);

        /**
         * 引入流
         */

        //流只能遍历一次
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);

        //使用for-each循环外部迭代
        List<String> names = new ArrayList<>();
        for (Dish d : menu) {
            names.add(d.getName());
        }

        //用背后的迭代器做外部迭代
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Dish d = iterator.next();
            names.add(d.getName());
        }

        //流:内部迭代
        names = menu.stream().map(Dish::getName).collect(toList());

        //流中增加中间操作
        names = menu.stream().filter(d -> {
            System.out.println("filtering " + d.getName());
            return d.getCalories() > 300;
        }).map(d -> {
            System.out.println("mapping " + d.getName());
            return d.getName();
        }).limit(3)
                .collect(toList());

        //终端操作 forEach count collect
        //中间操作 filter map limit sorted distinct
        menu.stream().forEach(System.out::println);
        long count = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .distinct()
                .limit(3)
                .count();

        // 筛选和切片
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());

        // 筛选不同元素
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        // 截断流 返回前n个元素
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());

        // 跳过元素 扔掉前n个元素
        dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());

        // 筛选前2个荤菜
        dishes = menu.stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(toList());

        //映射 map
        List<String> dishNames = menu.stream().map(Dish::getName)
                .collect(toList());

        /**
         * 使用流
         */

        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length).collect(toList());

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());

        //流的扁平化 给定单词列表 ["Hello","World"]，你想要返回列表["H","e","l", "o","W","r","d"]

        //以下方法无法解决
        words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());

        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

        //flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接 起来成为一个流。
        List<String> uniqueCharacters = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        //给定一个数组返回数组元素的平方
        numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map(x -> x * x)
                .collect(toList());

        //给定列表[1, 2, 3]和列表[3, 4]，应 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                        .collect(toList());

        //扩展前一个例子，只返回总和能被3整除的数对呢?例如(2, 4)和(3, 3)是可以的
        pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})
                ).collect(toList());

        //查找和匹配
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        boolean isHealthy = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);

        isHealthy = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);

        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian).findAny();

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        //查找第一个元素 能找出第一个平方 能被3整除的数:
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst();

        //规约

        //元素求和
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        //元素求乘积
        int product = numbers.stream().reduce(1, (a, b) -> a * b);

        //换一种方式写
        sum = numbers.stream().reduce(0, Integer::sum);
        //无初始值
        Optional<Integer> sums = numbers.stream().reduce((a, b) -> (a + b));

        //求最值
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);

        //求菜的数量
        count = menu.stream().map(d -> 1)
                .reduce(0, (a, b) -> a + b);

        count = menu.stream().count();
        //并行化
        sum = numbers.parallelStream().reduce(0, Integer::sum);

        //数值流
        //IntStream、DoubleStream和 LongStream，分别将流中的元素特化为int、long和double，从而避免了暗含的装箱成本
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        //转换为对象流
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        //数值范围
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        //由值创建流
        Stream<String> streamString = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        streamString.map(String::toUpperCase).forEach(System.out::println);

        //得到一个空流
        Stream<String> emptyStream = Stream.empty();

        //由数组创建流
        int[] numbersValue = {2, 3, 5, 7, 11, 13};
        int sumValue = Arrays.stream(numbersValue).sum();

        //由文件生成流
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {

        }

        //创建无限流
        Stream.iterate(0, n -> n + 2)
                .limit(10).forEach(System.out::println);

        //斐波那契数列
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0] + t[1]}).limit(10)
                .map(t -> t[0]).forEach(System.out::println);

        //生成随机数流
        Stream.generate(Math::random)
                .limit(5).forEach(System.out::println);

        //生成全是1的流
        IntStream ones = IntStream.generate(() -> 1);

        /**
         * 使用流收集数据
         */

        //统计数目
        long howManyDishes = menu.stream().collect(Collectors.counting());
        howManyDishes = menu.stream().count();

        //查找最大最小
        Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream()
                .collect(maxBy(dishCaloriesComparator));

        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        //菜肴热量总和、平均值、最大值和最小值
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));

        //连接字符串  joining工厂方法返回的收集器会把对流中每一个对象应用toString方法得到的所有字符串连接成一个字符串
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));

        // 规约汇总
        totalCalories = menu.stream().collect(reducing(
                0, Dish::getCalories, (i, j) -> i + j));

        mostCalorieDish = menu.stream().collect(reducing(
                (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

        totalCalories = menu.stream().collect(reducing(0,
                Dish::getCalories,
                Integer::sum));

        //分组
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(dish1 -> {
            if (dish1.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish1.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }));

        //多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish1 -> {
                            if (dish1.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish1.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        })));
        //按照分组收集数据
        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting()));

        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
                .collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));

        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream().collect(groupingBy(Dish::getType,
                summingInt(Dish::getCalories)));

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish1 -> {
                            if (dish1.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish1.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        },
                        toSet())));

        caloricLevelsByType = menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish1 -> {
                            if (dish1.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish1.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        },
                        toCollection(HashSet::new))));

        //分区 分区函数返回一个布尔值，这意味着得到的分组Map的键类型是Boolean，于是它最多可以 分为两组——true是一组，false是一组
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get)));
        //收集器接口


    }
    //质数和非质数
    public Map<Boolean, List<Integer>> partitionPrimes ( int n){
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        partitioningBy(candidate -> isPrime(candidate)));
    }

    public boolean isPrime(int candidate) {
        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
    }

    public enum CaloricLevel {DIET, NORMAL, FAT}
}
