package pattern.celei;

/**
 * Created by Administrator on 2017/3/10.
 */

/**
 * 制定策略规则
 */
public class StrategyContext {
    AbstractStrategy strategy = null;

    public void SetStrategy(AbstractStrategy strategy)
    {
        this.strategy = strategy;
    }

    public void Setup()
    {
        this.strategy.Setup();
    }

}
