package factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class ProductFactory {
    //工厂模式
    public static Product createProduct(String name) {
        switch (name) {
            case "loan":
                return new Loan();
            case "stock":
                return new Stock();
            case "bond":
                return new Bond();
            default:
                throw new RuntimeException("No such product " + name);
        }
    }

    Product p = ProductFactory.createProduct("loan");

    //使用Lambda表达式
    Supplier<Product> loanSupplier = Loan::new;
    Loan loan = (Loan) loanSupplier.get();

    //使用Map进行映射
    final static Map<String, Supplier<Product>> map = new HashMap<>();

    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static Product createProductByMap(String name) {
        Supplier<Product> p = map.get(name);
        if (p != null){
            return p.get();
        }
        throw new IllegalArgumentException("No such product " + name);
    }
}
