import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class PredicateTest {
    public static void main(String[] args) {
        List<String> listOfStrings = new ArrayList<>();
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for(T s: list){
            if(p.test(s)){
                results.add(s);
            }
        }
        return results;
    }
}
