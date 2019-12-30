package observer;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public interface Subject {
    void registerObserver(Observer o);

    void notifyObservers(String tweet);
}
