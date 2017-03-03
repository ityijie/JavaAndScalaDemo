package threadtest.wait_notify_insert_test;

/**
 * Created by Administrator on 2017/3/1.
 */
public class BackupA extends Thread{
    private DBTools dbTools;

    public BackupA(DBTools dbTools) {
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        super.run();
        dbTools.backupA();

    }
}
