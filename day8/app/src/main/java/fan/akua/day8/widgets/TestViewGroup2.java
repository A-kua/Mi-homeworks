package fan.akua.day8.widgets;

import static androidx.customview.widget.ViewDragHelper.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

import java.util.ArrayList;
import java.util.List;

public class TestViewGroup2 extends ViewGroup {
    private final List<List<View>> lines = new ArrayList<>();
    private final List<Integer> heights = new ArrayList<>();

    private ViewDragHelper mViewDragHelper;

    public TestViewGroup2(Context context) {
        super(context);
        init();
    }

    public TestViewGroup2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TestViewGroup2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mViewDragHelper = create(this, dragCB);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        lines.clear();
        heights.clear();

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        List<View> lineViews = new ArrayList<>();
        int lineWidth = 0;
        int lineHeight = 0;

        int parentWith = 0;
        int parentHeight = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);

            // 这玩意确实有必要
            if (view.getVisibility() == View.GONE) continue;

            LayoutParams params = view.getLayoutParams();

            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, params.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, params.height);
            view.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            int childMeasuredWidth = view.getMeasuredWidth();
            int childMeasuredHeight = view.getMeasuredHeight();

            // 超出了
            if (childMeasuredWidth + lineWidth > widthSize) {
                // 记录这行所有View
                lines.add(lineViews);
                // 记录这行的高度
                heights.add(lineHeight);

                parentHeight = parentHeight + lineHeight;
                parentWith = Math.max(parentWith, lineWidth);

                // 重置
                lineViews = new ArrayList<>();
                lineWidth = 0;
                lineHeight = 0;
            }

            lineViews.add(view);

            lineWidth = lineWidth + childMeasuredWidth;
            lineHeight = Math.max(lineHeight, childMeasuredHeight);
        }
        // 最后一行
        lines.add(lineViews);
        heights.add(lineHeight);
        parentHeight = parentHeight + lineHeight;
        parentWith = Math.max(parentWith, lineWidth);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int finalWidth = (widthMode == MeasureSpec.EXACTLY) ? widthSize : parentWith;
        int finalHeight = (heightMode == MeasureSpec.EXACTLY) ? heightSize : parentHeight;
        setMeasuredDimension(finalWidth, finalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineCount = lines.size();

        int currentL = getPaddingLeft();
        int currentT = getPaddingTop();

        for (int i = 0; i < lineCount; i++) {
            List<View> views = lines.get(i);

            int lineHeight = heights.get(i);
            for (View view : views) {
                int right = currentL + view.getMeasuredWidth();
                int bottom = currentT + view.getMeasuredHeight();
                view.layout(currentL, currentT, right, bottom);
                currentL = right;
            }

            // 每行都重置
            currentT = currentT + lineHeight;
            currentL = getPaddingLeft();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }


    private final ViewDragHelper.Callback dragCB = new Callback() {
        private int captureLeft, captureTop;

        @Override
        public boolean tryCaptureView(@NonNull View view, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return top;
        }

        @Override
        public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
            capturedChild.setBackgroundColor(Color.RED);
            Log.d("simon", capturedChild.hashCode() + "");
            captureLeft = capturedChild.getLeft();
            captureTop = capturedChild.getTop();
        }

        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            Log.d("simon", releasedChild.hashCode() + "");
            int left = releasedChild.getLeft() + releasedChild.getWidth() / 2;
            int top = releasedChild.getTop() + releasedChild.getTop() / 2;
            View viewUnder = findViewUnder(releasedChild, left, top);
            if (viewUnder == null) {
                mViewDragHelper.settleCapturedViewAt(captureLeft, captureTop);
                postInvalidateOnAnimation();
            } else {
                View capturedView = mViewDragHelper.getCapturedView();
                swapViews(indexOfChild(releasedChild), indexOfChild(viewUnder));
            }

        }

        @Override
        public void onViewDragStateChanged(int state) {
            if (state == ViewDragHelper.STATE_IDLE) {
                View capturedView = mViewDragHelper.getCapturedView();
                if (capturedView != null) {
                    capturedView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        }
    };

    private View findViewUnder(View self, float x, float y) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child != self
                    && child.getVisibility() == VISIBLE
                    && x >= child.getLeft() && x <= child.getRight()
                    && y >= child.getTop() && y <= child.getBottom()) {
                return child;
            }
        }
        return null;
    }

    private void swapViews(int position1, int position2) {
        View view1 = getChildAt(position1);
        View view2 = getChildAt(position2);
        removeViewAt(Math.max(position1, position2));
        removeViewAt(Math.min(position1, position2));
        addView(view2, Math.min(position1, position2));
        addView(view1, Math.min(position1, position2));
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            postInvalidateOnAnimation();
        }
    }
}
