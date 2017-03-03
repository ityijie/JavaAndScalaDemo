package threadtest.jointest;

/**
 * Created by Administrator on 2017/3/1.
 */

/**
 * join():等待子线程完成任务后,继续执行主线程
 * 内部调用wait()  所以有释放锁的特点
 * sleep() 不会释放锁
 */
public class Test {
    public static void main(String[] args) {
        try {
            MyThread threadTest = new MyThread();
            threadTest.start();
            //join(long) ,在指定时间内没有消息返回,则跳过,内部调用的是wait
            threadTest.join(1000);
            System.out.println("当threadTest对象执行完毕后回来执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
