package threadtest.synchronizedupdatenewvalue;

/**
 * Created by Administrator on 2017/3/1.
 */
public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.runMethod();
    }
}
