package fan.akua.day9.bean;

import java.util.List;

public class ApiResponse<T> {
    private int code;
    private T result;

    public int getCode() {
        return code;
    }

    public T getResult() {
        return result;
    }
}
