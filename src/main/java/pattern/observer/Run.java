package pattern.observer;

/**
 * Created by Administrator on 2017/3/10.
 */

/**
 * 相互绑定
 */
public class Run {

    public static void main(String[] args) {
        Subject subject = new Subject();

        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
