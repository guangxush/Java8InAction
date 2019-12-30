import java.util.Arrays;
import java.util.List;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class CustomerTest {
    public static void main(String[] args) {
        forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));
    }

    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T i:list){
            c.accept(i);
        }
    }
}
