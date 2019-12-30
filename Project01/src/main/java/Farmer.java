import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class Farmer {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"),
                new Apple(120, "red"));
        List<Apple> greenApples = FilterAppleOne.filterApplesByColor(inventory, "green");
        List<Apple> redApples = FilterAppleOne.filterApplesByColor(inventory, "red");
        List<Apple> apples = FilterAppleOne.filterApples(inventory, "green", 0, true);
        List<Apple> heavyApples = FilterAppleOne.filterApples(inventory, "", 150, false);


        //行为参数化的方式
        List<Apple> redAndHeavyApples = FilterAppleTwo.filterApples(inventory, new AppleRedAndHeavyPredicate());


        //使用匿名类
        redApples = FilterAppleThree.filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });

        //使用Lambda表达式
        List<Apple> result = FilterAppleFour.filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));

        //使用泛型之后的Lambda表达式
        redApples = FilterAppleFive.filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = FilterAppleFive.filter(numbers, (Integer i) -> i % 2 == 0);

        //使用排序
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        //改用泛型进行排序
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        //使用线程执行代码
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
        t = new Thread(() -> System.out.println("hello"));
    }
}
