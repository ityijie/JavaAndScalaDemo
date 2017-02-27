package ServiceImp;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 本机调试不通过,大爷
 * Created by Administrator on 2016/12/29.
 */
public class MyServiceImpl extends UnicastRemoteObject implements MyService {


    protected MyServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Server says, 'Hey'";
    }


    public static void main(String[] args) {

            try{
                MyService service = new MyServiceImpl();
                Naming.rebind("RemoteHello", service);
            } catch(Exception e){
                e.printStackTrace();
            }


    }


}
