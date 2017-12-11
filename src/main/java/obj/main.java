package obj;

/**
 * Created by Administrator on 2017/4/17.
 */
public class main {
    public static void main(String[] args) {
        //继承,对扩展开放：允许新增Animal子类；
        dog dog = new dog();
        dog.run();
        //多态,只需要接收Animal类型,不用修改animal
        runing(new cat());
        ceshi(1,2);
    }

    public static void runing(Animal an) {
        an.run();
    }

    public static int ceshi(int a, int b) {
        return a + b;
    }

}
