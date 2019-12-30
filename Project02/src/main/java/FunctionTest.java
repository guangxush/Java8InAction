import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class FunctionTest {
    public static void main(String[] args) {
        // [7, 2, 6]
        List<Integer> l = map(Arrays.asList("lambda", "in", "action"),
                (String s)->s.length());
    }
    public static<T,R> List<R> map(List<T> list, Function<T, R> f){
        List<R> result = new ArrayList<>();
        for(T s:list){
            result.add(f.apply(s));
        }
        return result;
    }
}
