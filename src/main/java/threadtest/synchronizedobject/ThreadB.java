package threadtest.synchronizedobject;

/**
 * Created by Administrator on 2017/2/28.
 */
public class ThreadB extends Thread {

    private  MyObject object;

    public ThreadB(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        object.methodB();
    }
}
