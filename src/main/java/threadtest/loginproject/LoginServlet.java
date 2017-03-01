package threadtest.loginproject;

/**
 * Created by Administrator on 2017/2/27.
 */
public class LoginServlet {
    private static String userNameRef;
    private static String passwordRef;
    synchronized public static void doPost(String userName,String passWord){
        try {
            userNameRef = userName;
            if(userName.equals("a")){
                Thread.sleep(5000);
            }
            passwordRef = passWord;
            System.out.println("userName :" + userName + "  passWord :" +passWord);

        }catch (InterruptedException e){
            e.printStackTrace();

        }

    }

}
