/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class Apple {
    private String color;
    private Integer weight;

    public Apple(){

    }

    public Apple(Integer weight){
        this.weight = weight;
    }

    public Apple(Integer weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
