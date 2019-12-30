package strategy;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class IsNumeric implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
