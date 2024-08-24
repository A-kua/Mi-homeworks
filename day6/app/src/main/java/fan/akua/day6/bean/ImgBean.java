package fan.akua.day6.bean;

import java.util.Objects;

public class ImgBean {
    private final String url;

    public ImgBean(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImgBean imgBean = (ImgBean) o;
        return Objects.equals(url, imgBean.url);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url);
    }
}
