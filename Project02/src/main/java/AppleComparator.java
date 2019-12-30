import java.util.Comparator;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
    }
}
