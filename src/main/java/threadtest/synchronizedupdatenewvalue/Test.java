package threadtest.synchronizedupdatenewvalue;

/**
 * Created by Administrator on 2017/3/1.
 */
public class Test {
    public static void main(String[] args) {

        try {
            Service service = new Service();
            ThreadA a = new ThreadA(service);
            a.start();
            Thread.sleep(1000);
            ThreadB b = new ThreadB(service);
            b.start();
            System.out.println("发出停止指令");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
