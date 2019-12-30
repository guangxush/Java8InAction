import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    /**
     * 建立新的结果容器:supplier方法
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        return () -> new ArrayList<T>();
        //    return ArrayList::new;
    }

    /**
     * 将元素添加到结果容器:accumulator方法
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (list, item) -> list.add(item);
        //    return List::add;
    }

    /**
     * 在遍历完流后，finisher方法必须返回在累积过程的最后要调用的一个函数，以便将累加 器对象转换为整个集合操作的最终结果
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * 合并两个结果容器:combiner方法
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2); return list1; };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }
}
