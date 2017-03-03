package threadtest.singleton;

/**
 * Created by Administrator on 2017/3/2.
 */
public class Run {
    public static void main(String[] args) {
        //加载的是用一个hashCode值,对象是同一个,立即加载
     /*   new MyThread().start();
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();*/

        //加载的是用一个hashCode值,对象是同一个,懒加载
        //错误的单例模式,多线程情况下会创建多个实例
        //解决方案,加synchronized()
       /* new MyThreadDely().start();
        new MyThreadDely().start();
        new MyThreadDely().start();*/

        //正确版的懒汉加载单例模式 可以应用
      /*  new RightThread().start();
        new RightThread().start();
        new RightThread().start();*/

        //内部静态类实现单例模式 可以应用
       /* new InnerThread().start();
        new InnerThread().start();
        new InnerThread().start();
        new InnerThread().start();*/

        //StaticObject
        new StaticThread().start();
        new StaticThread().start();
        new StaticThread().start();
        new StaticThread().start();



    }
}
