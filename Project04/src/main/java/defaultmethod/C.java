package defaultmethod;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class C extends D implements A, B{
    public static void main(String[] args) {
        new C().hello();
        //Hello from B
    }
}
