package threadtest.priority;

/**
 * Created by Administrator on 2017/2/28.
 */

/**
 * 1.优先级具有继承性,如果A线程启动B线程,那么A和B的优先级是一样的
 */
public class MyThreadCopy extends Thread {
    @Override
    public void run() {
        System.out.println("MyThreadCopy tun priority =  " + this.getPriority());


    }
}
