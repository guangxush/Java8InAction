package strategy;

import strategy.IsAllLowerCase;
import strategy.IsNumeric;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class StrategyTest {
    public static void main(String[] args) {
        //传统的策略模式
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");

        //使用Lambda改进的策略模式
        numericValidator = new Validator((String s) -> s.matches("[a-z]+"));
        b1 = numericValidator.validate("aaaa");
        lowerCaseValidator = new Validator((String s) -> s.matches("\\d+"));
        b2 = lowerCaseValidator.validate("bbbb");

    }
}
