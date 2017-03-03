package threadtest.singleton;

/**
 * Created by Administrator on 2017/3/2.
 */
public class RightThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println(RightMyObject
                .getInstance().hashCode());
    }
}
