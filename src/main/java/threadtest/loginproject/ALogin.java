package threadtest.loginproject;

/**
 * Created by Administrator on 2017/2/27.
 */
public class ALogin extends  Thread {
    @Override
    public void run() {
        super.run();
        LoginServlet.doPost("a","aa");
    }
}
