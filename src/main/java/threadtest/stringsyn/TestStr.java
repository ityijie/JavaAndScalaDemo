package threadtest.stringsyn;

/**
 * Created by Administrator on 2017/2/28.
 */


/***
 * String a = "b"
 * String c = "b"  以上两者是一样的,所以认为是一个锁
 * JVM 中具有String常量池缓存的功能,
 * 将synchronized(String)同步块与String联合使用,会出现无法获取锁
 * 一般synchronized代码块不能不使用String作为锁对象 最好是new Object()实例化一个
 */
public class TestStr {
    public static void main(String[] args) {
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        ThreadB b = new ThreadB(service);
        b.setName("B");
        a.start();
        b.start();
        //只会打印A
    }


}
