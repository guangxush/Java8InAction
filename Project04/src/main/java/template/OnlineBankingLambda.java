package template;

import java.util.function.Consumer;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class OnlineBankingLambda{

    public void processCustomer(int id,  Consumer<Customer> makeCustomerHappy){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    void makeCustomerHappy(Customer c){
        return;
    }
}
