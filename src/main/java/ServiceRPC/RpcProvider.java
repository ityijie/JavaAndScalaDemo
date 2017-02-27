package ServiceRPC;

/**
 * Created by Administrator on 2016/12/29.
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, HelloService.class, 9000);
    }
}
