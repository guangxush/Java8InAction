/**
 * @author: guangxush
 * @create: 2019/12/30
 */
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
