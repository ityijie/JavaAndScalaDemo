package threadtest.singleton;

/**
 * Created by Administrator on 2017/3/2.
 */
public class InnerThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println(InnerObject.getInstance().hashCode());
    }
}
