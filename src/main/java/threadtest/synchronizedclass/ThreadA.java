package threadtest.synchronizedclass;

/**
 * Created by Administrator on 2017/2/28.
 */
public class ThreadA extends Thread {
    private  Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.printA();
    }
}
