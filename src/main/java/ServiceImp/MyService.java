package ServiceImp;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Administrator on 2016/12/29.
 */
public interface MyService extends Remote {
    public String sayHello() throws RemoteException;
}
