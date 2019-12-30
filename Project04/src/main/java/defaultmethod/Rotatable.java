package defaultmethod;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public interface Rotatable {
    void setRotationAngle(int angleInDegrees);

    int getRotationAngle();

    default void rotateBy(int angleInDegrees, int angle) {
        setRotationAngle((getRotationAngle() + angle) % 360);
    }
}
