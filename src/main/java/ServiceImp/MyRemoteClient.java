package ServiceImp;

import java.rmi.Naming;

/**
 * Created by Administrator on 2016/12/29.
 */
public class MyRemoteClient {

    public static void main(String[] args){
        new MyRemoteClient().go();
    }

    public void go() {
        try {
            MyService service = (MyService) Naming.lookup("rmi://192.168.1.106/RemoteHello");
            String s = service.sayHello();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
