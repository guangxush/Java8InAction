import java.util.ArrayList;
import java.util.List;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class PrettyPrintApple {
    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter){
        for(Apple apple:inventory){
            String output = appleFormatter.accept(apple);
            System.out.println(output);
        }
    }
    public void test(){
        List<Apple> inventory = new ArrayList<Apple>();
        prettyPrintApple(inventory, new AppleFancyFormatter());
    }
}
