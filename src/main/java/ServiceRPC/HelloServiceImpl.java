package ServiceRPC;

/**
 * Created by Administrator on 2016/12/29.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "Hello";
    }

    @Override
    public String hello(String name) {
        return "Hello," + name;
    }

}
