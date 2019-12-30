package optional;

import java.util.Optional;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class TestOptional {
    public static void main(String[] args) {
        Optional<Car> optCar = Optional.empty();
        optCar = Optional.of(new Car());
        optCar = Optional.ofNullable(null);

        String name = null;
        Insurance insurance = new Insurance();
        if (insurance != null) {
            name = insurance.getName();
        }

        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> names = optInsurance.map(Insurance::getName);

        Person person = new Person();
        Optional<Person> optPerson = Optional.of(person);
        Optional<String> name1 =
                optPerson.flatMap(Person::getCar)
                        .flatMap(Car::getInsurance)
                        .map(Insurance::getName);

        optInsurance.filter(insurance1 ->
                "CambridgeInsurance".equals(insurance1.getName())).ifPresent(x -> System.out.println("ok"));
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        // 不同的保险公司提供的查询服务
        // 对比所有数据
        Insurance cheapestCompany = new Insurance();
        return cheapestCompany;
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(
            Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person
                .filter(p -> p.getAge().get() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
