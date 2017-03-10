package pattern.guolv;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public class CriteriaMale implements  Criteria {


    /***
     * 过滤实现规则
     * 区别性别
     * @param
     * @return
     */
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        ArrayList<Person> malePersons = new ArrayList<>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("MALE")){
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
