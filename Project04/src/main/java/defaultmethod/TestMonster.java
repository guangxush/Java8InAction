package defaultmethod;

/**
 * @author: guangxush
 * @create: 2019/12/30
 */
public class TestMonster {
    public static void main(String[] args) {
        Monster m = new Monster();
        m.rotateBy(180, 90);
        m.moveVertically(10);

        //如果一个类使用相同的函数签名从多个地方(比如另一个类或接口)继承了方法，通过三条 规则可以进行判断。
        //1.类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优先级。
        //2.如果无法依据第一条进行判断，那么子接口的优先级更高:函数签名相同时，优先选择拥有最具体实现的默认方法的接口，即如果B继承了A，那么B就比A更加具体
        //3.最后，如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法，显式地选择使用哪一个默认方法的实现。
    }
}
