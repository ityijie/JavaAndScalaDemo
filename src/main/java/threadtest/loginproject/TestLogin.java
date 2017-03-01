package threadtest.loginproject;

/**
 * Created by Administrator on 2017/2/27.
 */

/**
 *  两个线程同时调用一个方法
 *  synchronized 锁住方法,必须等A执行完毕
 */
public class TestLogin {
    public static void main(String[] args) {

        new ALogin().start();
        new BLogin().start();
    }
}
