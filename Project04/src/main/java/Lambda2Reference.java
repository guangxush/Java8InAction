import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class Lambda2Reference {
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

        Map<LearnStream.CaloricLevel, List<Dish>> dishesByCaloricLevel =
                menu.stream()
                        .collect(groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return LearnStream.CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return LearnStream.CaloricLevel.NORMAL;
                            } else {
                                return LearnStream.CaloricLevel.FAT;
                            }
                        }));

        //将引用单独转换成一个方法
        //Dish.getCaloricLevel()
        dishesByCaloricLevel = menu.stream().collect(groupingBy(Dish::getCaloricLevel));


        //从命令式的数据处理切换到Stream
        menu.parallelStream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .collect(toList());
    }
}
