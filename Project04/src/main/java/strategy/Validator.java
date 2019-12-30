package strategy;

import strategy.ValidationStrategy;

/**
 * 策略模式
 * @author: guangxush
 * @create: 2019/12/30
 */
public class Validator {
    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }
}
