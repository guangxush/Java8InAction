/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public interface AppleFormatter {
    String accept(Apple apple);
}

class AppleFancyFormatter implements AppleFormatter {
    public String accept(Apple apple) {
        String characheristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A" + characheristic + " " + apple.getColor() + " apple";
    }
}

class AppleSimpleFormatter implements AppleFormatter{
    public String accept(Apple apple){
        return "An apple of " + apple.getWeight() + "g";
    }
}