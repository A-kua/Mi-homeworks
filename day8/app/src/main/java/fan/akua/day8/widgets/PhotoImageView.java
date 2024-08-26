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
