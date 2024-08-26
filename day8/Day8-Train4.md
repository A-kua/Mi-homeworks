## Day8-Train4

相关的文件如下：
1. [RotateGestureDetector.java](app/src/main/java/fan/akua/day8/widgets/RotateGestureDetector.java)
2. [PhotoImageView.java](app/src/main/java/fan/akua/day8/widgets/PhotoImageView.java)

> 纸上得来终觉浅，绝知此事要躬行。

这玩意正好前几天自己软件需要，写过一个，这里改一改直接用了。

### 编写代码

首先是旋转的监听，因为安卓原生是没有给旋转的实现的。

```java
package fan.akua.day8.widgets;


import android.view.MotionEvent;

import androidx.annotation.NonNull;

public class RotateGestureDetector {

    private static final int MAX_DEGREES_STEP = 120;

    private final OnRotateListener mListener;

    private float mPrevSlope;
    private float focusX, focusY;

    public RotateGestureDetector(@NonNull OnRotateListener l) {
        mListener = l;
    }


    public void onTouchEvent(@NonNull MotionEvent event) {
        final int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getPointerCount() == 2) mPrevSlope = calculateSlope(event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() > 1) {
                    float mCurrSlope = calculateSlope(event);

                    double currDegrees = Math.toDegrees(Math.atan(mCurrSlope));
                    double prevDegrees = Math.toDegrees(Math.atan(mPrevSlope));

                    float mDegrees = (float) (currDegrees - prevDegrees);

                    if (Math.abs(mDegrees) <= MAX_DEGREES_STEP) {
                        mListener.onRotate(mDegrees, focusX, focusY);
                    }
                    mPrevSlope = mCurrSlope;
                }
                break;
            default:
                break;
        }
    }


    strictfp
    private float calculateSlope(MotionEvent event) {
        final float x1 = event.getX(0);
        final float y1 = event.getY(0);
        final float x2 = event.getX(1);
        final float y2 = event.getY(1);
        focusX = (x2 + x1) / 2;
        focusY = (y2 + y1) / 2;
        return (y2 - y1) / (x2 - x1);
    }

    @FunctionalInterface
    public interface OnRotateListener {
        void onRotate(float degrees, float focusX, float focusY);
    }
}
```

编写自定义ImageView。这里用Matrix模式，好操作。

```java
package fan.akua.day8.widgets;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public final class PhotoImageView extends AppCompatImageView
        implements ScaleGestureDetector.OnScaleGestureListener,
        GestureDetector.OnGestureListener,
        RotateGestureDetector.OnRotateListener {
    private final RotateGestureDetector mRotateGestureDetector;
    private final ScaleGestureDetector mScaleGestureDetector;
    private final GestureDetector mGestureDetector;
    private final Matrix mShowMatrix;


    public PhotoImageView(Context context) {
        this(context, null);
    }

    public PhotoImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRotateGestureDetector = new RotateGestureDetector(this);
        mScaleGestureDetector = new ScaleGestureDetector(context, this);
        mGestureDetector = new GestureDetector(context, this);
        mShowMatrix = new Matrix();

        super.setScaleType(ScaleType.MATRIX);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        mRotateGestureDetector.onTouchEvent(event);

        mScaleGestureDetector.onTouchEvent(event);

        setImageMatrix(mShowMatrix);
        return true;
    }


    @Override
    public boolean onScale(@NonNull ScaleGestureDetector detector) {
        float scaleFactor = detector.getScaleFactor();

        if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor))
            return false;

        mShowMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
        return true;
    }

    @Override
    public boolean onScaleBegin(@NonNull ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(@NonNull ScaleGestureDetector detector) {
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        mShowMatrix.postTranslate(-distanceX, -distanceY);
        setImageMatrix(mShowMatrix);
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {
        Toast.makeText(getContext(), "longPress", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onRotate(float degrees, float focusX, float focusY) {
        mShowMatrix.postRotate(degrees, focusX, focusY);
    }
}
```

### 运行效果如下

[视频无法播放请点击我](vx_images/Screen_recording_20240826_162113.mp4)

<div>
    <video src="vx_images/Screen_recording_20240826_162113.mp4"></video>
</div>
