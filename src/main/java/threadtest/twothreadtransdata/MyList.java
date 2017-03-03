package threadtest.twothreadtransdata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
public class MyList {
    private List list = new ArrayList();
    public void add(){
        list.add("测试机");
    }

    public int size(){
        return list.size();
    }
}
