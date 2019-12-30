import java.util.ArrayList;
import java.util.List;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class FilterAppleOne {
    // 直接筛选绿色苹果
    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple:inventory){
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }
    //给方法加参数过滤颜色
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple:inventory){
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }
    //根据重量筛选
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple:inventory){
            if(apple.getWeight() > weight){
                result.add(apple);
            }
        }
        return result;
    }
    //多个属性相结合, shit code
    public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple:inventory){
            if((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)){
                result.add(apple);
            }
        }
        return result;
    }
}
