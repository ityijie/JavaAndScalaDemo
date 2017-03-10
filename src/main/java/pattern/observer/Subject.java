package pattern.observer;

/**
 * Created by Administrator on 2017/3/10.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 被检测对象
 */
public class Subject {
    private List<Observer> observers = new ArrayList<Observer>();


    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        //发生更改,通知
        notifyAllObservers();
    }

    //绑定观察器
    public void attach(Observer observer) {
     observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
