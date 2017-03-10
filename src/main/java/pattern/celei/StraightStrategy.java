package pattern.celei;

/**
 * Created by Administrator on 2017/3/10.
 */
public class StraightStrategy implements AbstractStrategy {

    @Override
    public void Setup() {
        /*编写执行逻辑*/
        System.out.println("给个别客户发送B内容");
    }
}
