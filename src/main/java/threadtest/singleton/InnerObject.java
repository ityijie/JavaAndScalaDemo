package threadtest.singleton;

/**
 * Created by Administrator on 2017/3/2.
 */

/**
 *  静态内置类实现单例模式
 *  由于内部静态类只会被加载一次，故该实现方式时线程安全的！
 *  静态内置类可以达到线程安全问题,但是如果遇到序列化对象时,运行时得到的结果还是多例
 */
public class InnerObject {
    private static class InnerObjectHandler{
        private static InnerObject innerObject = new InnerObject();
    }

    public InnerObject() {
    }

    public static InnerObject getInstance(){
        return InnerObjectHandler.innerObject;
    }
}
