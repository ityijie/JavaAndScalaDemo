package threadtest.synchronizedupdatenewvalue;

/**
 * Created by Administrator on 2017/3/1.
 */
public class ThreadB extends Thread {
    public ThreadB(Service service) {
        this.service = service;
    }

    private  Service service;
    @Override
    public void run() {
        service.stopMethod();
    }
}
