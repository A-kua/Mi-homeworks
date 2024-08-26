package fan.akua.day8.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class LineEditText extends EditText {
    private final Rect rect = new Rect();
    private final Paint paint = new Paint();

    public LineEditText(@NonNull Context context) {
        super(context);
        init();
    }

    public LineEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LineEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < getLineCount(); i++) {
            getLineBounds(i, rect);
            canvas.drawRect(rect, paint);
        }
    }
}
