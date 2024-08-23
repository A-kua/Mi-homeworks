package fan.akua.day5.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class UnitUtils {
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
