package defaultmethod;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public interface B extends A{
    @Override
    default void hello() {
        System.out.println("Hello from B");
    }
}
