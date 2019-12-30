import java.util.ArrayList;
import java.util.List;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
//行为参数化
public class FilterAppleTwo {
    //根据条件筛选
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

}
