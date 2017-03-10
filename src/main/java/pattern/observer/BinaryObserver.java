package pattern.observer;

/**
 * Created by Administrator on 2017/3/10.
 */

/**
 * 观察器实现逻辑
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void update() {
        System.out.println("更新资料:"+ subject.getState());

    }
}
