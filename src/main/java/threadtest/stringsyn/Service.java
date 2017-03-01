package threadtest.stringsyn;

/**
 * Created by Administrator on 2017/2/28.
 */
public class Service {
    public static void print (String stringparam){
        synchronized (stringparam){
            while (true){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
