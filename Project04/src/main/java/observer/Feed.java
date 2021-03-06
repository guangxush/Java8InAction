package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class Feed implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }
}
