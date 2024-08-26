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

