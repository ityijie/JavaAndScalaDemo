package ServiceRPC;

/**
 * Created by Administrator on 2016/12/29.
 */
public class RpcConsumer {
    public static void main(String[] args) throws Exception {
        HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 9000);
        String result = service.hello("rod");
        System.out.println(result);
    }

}
