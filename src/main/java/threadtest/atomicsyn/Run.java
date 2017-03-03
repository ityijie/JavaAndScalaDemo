package threadtest.atomicsyn;

/**
 * Created by Administrator on 2017/3/1.
 */


/***
 * atomic : 本质是原子操作 (AtomicInteger)
 *        AtomicInterger原子类在具有 逻辑性 的情况下输出结果也具有随机性(也需要用synchronized来解决)
 *        操作随机性,但是结果是原子性(结果是正确的)
 */
public class Run {
    public static void main(String[] args) {
        AddCountThread addCountThread = new AddCountThread();
        Thread t1 = new Thread(addCountThread);
        Thread t2 = new Thread(addCountThread);
        Thread t3 = new Thread(addCountThread);
        Thread t4 = new Thread(addCountThread);
        Thread t5 = new Thread(addCountThread);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
