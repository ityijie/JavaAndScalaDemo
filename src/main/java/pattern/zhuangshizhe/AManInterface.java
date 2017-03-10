package pattern.zhuangshizhe;

/**
 * Created by Administrator on 2017/3/10.
 */
public abstract class AManInterface implements PerInterface {
    protected PerInterface perInterface;

    public AManInterface(PerInterface perInterface) {
        this.perInterface = perInterface;
    }

    public void eat(){
        perInterface.eat();
    }


}
