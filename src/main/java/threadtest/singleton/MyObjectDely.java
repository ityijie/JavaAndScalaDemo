package threadtest.singleton;

/**
 * Created by Administrator on 2017/3/2.
 */

/**
 * 多线程下,不能保证单例
 * 解决方案,加上synchronized关键字
 */
public class MyObjectDely {
    private static MyObjectDely myObjectDely;

    public MyObjectDely() {
    }

    //synchronized 加载方法上,对运行效率有影响,优先放在代码块
    public static MyObjectDely getInstance() {
        try {

            synchronized (MyObjectDely.class) {
                if (myObjectDely != null) {
                } else {
                    Thread.sleep(1000);
                    myObjectDely = new MyObjectDely();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myObjectDely;
    }
}
