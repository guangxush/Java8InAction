package defaultmethod;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public interface A {
    default void hello(){
        System.out.println("Hello from A");
    }
}
