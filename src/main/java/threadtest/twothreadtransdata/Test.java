package threadtest.twothreadtransdata;

/**
 * Created by Administrator on 2017/3/1.
 */
public class Test {
    public static void main(String[] args) {
        MyList myList = new MyList();
        ThreadA a = new ThreadA(myList);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(myList);
        b.setName("B");
        b.start();
    }
}
