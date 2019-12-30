/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        FISH, MEAT, OTHER
    }

    public LearnStream.CaloricLevel getCaloricLevel() {
        if (this.getCalories() <= 400) {
            return LearnStream.CaloricLevel.DIET;
        } else if (this.getCalories() <= 700) {
            return LearnStream.CaloricLevel.NORMAL;
        } else {
            return LearnStream.CaloricLevel.FAT;
        }
    }
}

