package fan.akua.day6.events;

import fan.akua.day6.bean.MultiBean;

public class StarEvent {
    public MultiBean bean;
    public int position;

    public StarEvent(MultiBean bean, int position) {
        this.bean = bean;
        this.position = position;
    }
}
