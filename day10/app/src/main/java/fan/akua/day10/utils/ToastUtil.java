package fan.akua.day10.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private static volatile ToastUtil toastUtil;

    public static ToastUtil getInstance(Context context) {
        if (toastUtil == null) {
            synchronized (ToastUtil.class) {
                if (toastUtil == null) {
                    toastUtil = new ToastUtil(context);
                }
            }
        }
        return toastUtil;
    }

    private Context context;

    private ToastUtil(Context context) {
        this.context = context;
    }

    /**
     * 展示一个短文本
     *
     * @param text 文本
     */
    public void toastShort(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 展示一段长文本
     *
     * @param text 文本
     */
    public void toastLong(String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
/**
 * 使用了Activity的Context
 * 匿名内部类持有外部对象
 * 内部类持有外部对象
 */