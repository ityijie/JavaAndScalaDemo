package pattern.celei;

/**
 * Created by Administrator on 2017/3/10.
 */
public class Run {

    /**
     * 根据不同对象,实现的方法一样,但是内容不一样
     *
     * 策略模式的决定权在用户，系统本身提供不同算法的实现，新增或者删除算法，对各种算法做封装。
     * 因此，策略模式多用在算法决策系统中，外部用户只需要决定用哪个算法即可。
     * @param args
     */
    public static void main(String[] args) {
        StrategyContext context = new StrategyContext();

        //设置“随机策略“
        context.SetStrategy(new RandStrategy());

        context.Setup();

        //设置 ”直接发送“
        context.SetStrategy(new StraightStrategy());

        context.Setup();
    }
}
