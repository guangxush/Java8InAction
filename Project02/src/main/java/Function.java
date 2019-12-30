/**
 * @author: guangxush
 * @create: 2019/12/30
 */
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
