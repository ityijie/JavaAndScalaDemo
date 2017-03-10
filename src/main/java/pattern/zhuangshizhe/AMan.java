package pattern.zhuangshizhe;

/**
 * Created by Administrator on 2017/3/10.
 */
public class AMan extends AManInterface {
    public AMan(PerInterface perInterface) {
        super(perInterface);
    }

    @Override
    public void eat() {
        super.eat();
    }

    public   void eatmore(){
        System.out.println("eat more.....");
    }
}
