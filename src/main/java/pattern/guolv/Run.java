package pattern.guolv;

/**
 * Created by Administrator on 2017/3/10.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器模式
 *
 *
 * 我们在给用户做订单催付通知的时候，会有这样的一种场景，用户在系统后台设置一组可以催付的规则，比如说订单金额大于xx元，非黑名单用户，
 * 来自哪个地区，已购买过某个商品，指定某个营销活动的人等等这样的条件，如果这时用户在淘宝上下了一个订单，那程序要判断的就是看一下此
 * 订单是否满足这些规则中的某一个，如果满足，我们给他发送催付通知，这种场景是很多做CRM的同学都会遇到的问题，那针对这种场景，如何更好
 * 的规划业务逻辑呢？
 */
public class Run {
    public static void main(String[] args) {

        //添加各种类型人物
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("Robert", "Male", "Single"));
        persons.add(new Person("John", "Male", "Married"));
        persons.add(new Person("Laura", "Female", "Married"));
        persons.add(new Person("Diana", "Female", "Single"));
        persons.add(new Person("Mike", "Male", "Single"));
        persons.add(new Person("Bobby", "Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("\nFemales: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("\nSingle Males: ");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("\nSingle Or Females: ");
        printPersons(singleOrFemale.meetCriteria(persons));


    }
    public static void printPersons(List<Person> persons){
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName()
                    +", Gender : " + person.getGender()
                    +", Marital Status : " + person.getMaritalStatus()
                    +" ]");
        }
    }

}
