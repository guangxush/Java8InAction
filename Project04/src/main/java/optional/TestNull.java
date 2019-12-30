package optional;

import optional.origin.Car;
import optional.origin.Insurance;
import optional.origin.Person;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class TestNull {
    public String getCarInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }

    public String getCarInsuranceNameDeep(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null){
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }

    public String getCarInsuranceNameWidth(Person person) {
        if (person == null) {
            return "Unknown";
        }
        Car car = person.getCar(); if (car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance(); if (insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }

}
