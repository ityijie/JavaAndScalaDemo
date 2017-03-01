package threadtest.synchronizedobject;

/**
 * Created by Administrator on 2017/2/28.
 */
public class ThreadA extends Thread {

    private  MyObject object;

    public ThreadA(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        super.run();
        object.methodA();
    }
}
