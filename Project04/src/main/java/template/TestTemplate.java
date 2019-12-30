package template;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class TestTemplate {
    public static void main(String[] args) {
        new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello " + c.getName()));
    }

}
