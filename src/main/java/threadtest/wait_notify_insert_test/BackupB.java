package threadtest.wait_notify_insert_test;

/**
 * Created by Administrator on 2017/3/1.
 */
public class BackupB extends Thread{
    private DBTools dbTools;

    public BackupB(DBTools dbTools) {
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        super.run();
        dbTools.backupB();

    }
}
