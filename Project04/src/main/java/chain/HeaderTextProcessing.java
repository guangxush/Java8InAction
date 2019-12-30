package chain;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class HeaderTextProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return "From Raoul, Mario and Alan: " + input;
    }


}
