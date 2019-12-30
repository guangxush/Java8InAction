/**
 * @author: guangxush
 * @create: 2019/12/30
 */
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
