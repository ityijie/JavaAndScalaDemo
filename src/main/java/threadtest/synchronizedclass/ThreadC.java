package threadtest.synchronizedclass;

/**
 * Created by Administrator on 2017/2/28.
 */
public class ThreadC extends Thread {
    private  Service service;

    public ThreadC(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.printC();
    }
}
