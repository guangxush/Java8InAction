/**
 * @author: guangxush
 * @create: 2020/01/02
 */
public class Rate {

    public enum Money {
        EUR(0.7), USD(0.6);

        private double value;

        Money(double value) {
            this.value = value;
        }
    }

    public double getRate(Money moneyOrigin, Money moneyTarget) {
        return moneyOrigin.value / moneyTarget.value;
    }

}
