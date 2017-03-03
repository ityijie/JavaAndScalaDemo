package threadtest.singleton;

/**
 * Created by Administrator on 2017/3/2.
 */
public class StaticObject {
    private static StaticObject instance = null;

    public StaticObject() {
    }

    static {
        instance = new StaticObject();
    }

    public static StaticObject getInstance(){
        return instance;
    }
}

