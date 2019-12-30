import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
//使用函数式接口传递行为
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
