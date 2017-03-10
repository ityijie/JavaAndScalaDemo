package pattern.celei;

/**
 * Created by Administrator on 2017/3/10.
 */

/***
 * 随意发送
 */
public class RandStrategy implements AbstractStrategy{
    @Override
    public void Setup() {
         /*编写执行逻辑*/
        System.out.println("给全部客户发送A内容");
    }
}
