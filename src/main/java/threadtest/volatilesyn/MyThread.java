package threadtest.volatilesyn;

/**
 * Created by Administrator on 2017/2/28.
 */

/**
 * //synchronized 解决该volatile 原子性问题
 //注意一定要加static,作用于整个类(因为该变量是属于该类)
 */
public class MyThread extends Thread {
     volatile  public static int count;
    //synchronized 解决该volatile 原子性问题
    //注意一定要加static,作用于整个类(因为该变量是属于该类)
    synchronized  private  static void addCount(){
        for(int i = 0; i < 100 ; i++){

            count++;
        }
        System.out.println("count = "+count );
    }

    @Override
    public void run() {
        addCount();
    }
}
