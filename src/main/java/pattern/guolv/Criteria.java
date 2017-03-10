package pattern.guolv;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface Criteria {
    /**
     * 自定义过滤的标准 接口
     */
    public List<Person> meetCriteria(List<Person> persons);
}
