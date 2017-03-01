package threadtest.synchronizedclass;

/**
 * Created by Administrator on 2017/2/28.
 */
public class ThreadB extends Thread {
    private  Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.printB();
    }
}
