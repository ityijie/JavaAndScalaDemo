package threadtest.dirtyread;

/**
 * Created by Administrator on 2017/2/28.
 */


/***
 * 重锁,一个对象,在调用setValue后,再没有释放锁后,还可以继续调用 synchronized 的方法
 *
 * setValue(){
 *     getValue
 * }
 */
public class PublicVar {

    public String userName = "A";
    public String passWord = "AA";

    synchronized public void setValue(String userName,String passWord){
        try {
            this.userName = userName;
            Thread.sleep(3000);
            this.passWord = passWord;
            System.out.println("setValue method thread name = "
                    + Thread.currentThread().getName()
                    + " userName : " + userName
                    + " password : " + passWord);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    synchronized  public void getValue(){
        System.out.println("getValue method thread name = "
                + Thread.currentThread().getName()
                + " userName : " + userName
                + " password : " + passWord);

    }



}
