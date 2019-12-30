package observer;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class Guardian implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")) {
            System.out.println("Yet another news in London... " + tweet);
        }
    }
}
