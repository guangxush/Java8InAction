package template;

import java.util.function.Consumer;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public abstract class OnlineBanking {
    public void processCustomer(int id){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    public void processCustomerw2(int id,  Consumer<Customer> makeCustomerHappy){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    abstract void makeCustomerHappy(Customer c);
}
