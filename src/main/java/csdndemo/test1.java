package csdndemo;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/11/1.
 */
public class test1 {
    public static void main(String[] args) {
        new ReentrantLock();
        ExecutorService exrcutor = Executors.newCachedThreadPool();
        Future<Integer> result = exrcutor.submit(new task());
        exrcutor.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");
        System.out.println("2");
        try {
            System.out.println("task运行结果"+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}


class task implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0 ;
        for (int i = 0; i < 100; i++) {
            System.out.println("1");
            sum += i;
        }
        return sum;
    }
}
