package threadtest.singleton;

/**
 * Created by Administrator on 2017/3/2.
 */
public class MyThreadDely extends Thread{
    @Override
    public void run() {
        System.out.println(MyObjectDely.getInstance().hashCode());
    }
}
