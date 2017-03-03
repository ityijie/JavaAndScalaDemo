package threadtest.p_r_test;

/**
 * Created by Administrator on 2017/3/1.
 */

/**
 * 多生产,多消费,容易造成假死状态,都在waiting
 *  解决方法,用notifyall()取代 notify()
 */
public class Run  {
    public static void main(String[] args) {
        Object lock = new Object();
        P p = new P(lock);
        C c = new C(lock);
        ThreadP[] pThread = new ThreadP[2];
        ThreadC[] cThread = new ThreadC[5];
        for(int i = 0; i < 2; i++){
            pThread [i] = new ThreadP((p));
            pThread [i].setName("生产者"+ (i+1));
            pThread [i].start();

        }
        for(int i = 0; i < 5; i++){
            cThread [i] = new ThreadC((c));
            cThread [i].setName("消费者"+ (i+1));
            cThread [i].start();
        }


    }
}
