package optional;

import java.util.Optional;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class Person {
    private Optional<Car> car;
    private Optional<Integer> age;

    public Optional<Car> getCar() {
        return car;
    }

    public Optional<Integer> getAge() {
        return age;
    }
}
