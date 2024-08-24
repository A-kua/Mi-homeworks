package fan.akua.day6.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.Objects;

public class MultiBean implements MultiItemEntity, Serializable {
    public static enum Type {
        Img, Txt
    }

    public static MultiBean createImg(String url) {
        MultiBean bean = new MultiBean();
        bean.type = Type.Img;
        bean.imgUrl = url;
        return bean;
    }

    public static MultiBean createTxt(String txt) {
        MultiBean bean = new MultiBean();
        bean.type = Type.Txt;
        bean.txt = txt;
        return bean;
    }

    private boolean isStated;
    private Type type;
    private String imgUrl;
    private String txt;

    private MultiBean() {

    }

    @Override
    public int getItemType() {
        return type.ordinal();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTxt() {
        return txt;
    }

    public boolean isStated() {
        return isStated;
    }

    public void setStated(boolean stated) {
        isStated = stated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiBean bean = (MultiBean) o;
        return type == bean.type && Objects.equals(imgUrl, bean.imgUrl) && Objects.equals(txt, bean.txt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, imgUrl, txt);
    }
}
