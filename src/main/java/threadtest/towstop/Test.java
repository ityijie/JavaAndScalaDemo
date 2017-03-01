package threadtest.towstop;

/**
 * Created by Administrator on 2017/2/28.
 */

/**
 * 不同的方法用不通的锁的,避免业务死锁,
 */
public class Test {

    public static void main(String[] args) {
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        a.start();
        ThreadB b = new ThreadB(service);
        b.start();


    }
}
