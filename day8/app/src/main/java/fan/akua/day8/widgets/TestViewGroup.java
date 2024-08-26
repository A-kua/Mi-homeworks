package fan.akua.day8.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class TestViewGroup extends ViewGroup {

    private final List<List<View>> lines = new ArrayList<>();
    private final List<Integer> heights = new ArrayList<>();


    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    private View draggedView = null;
    private int draggedViewPosition = -1;

    private float tmpX, tmpY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录被按下的 view 及其位置
                draggedView = findViewUnder(event.getX(), event.getY());
                draggedViewPosition = indexOfChild(draggedView);
                Log.d("TestViewGroup", "draggedView " + draggedView + " index " + draggedViewPosition);
                tmpX = event.getX();
                tmpY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 如果有 view 被按下且正在移动
                if (draggedView != null) {
                    draggedView.setTranslationX(event.getX() - tmpX);
                    draggedView.setTranslationY(event.getY() - tmpY);
                    postInvalidateOnAnimation();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // 找到当前拖动 view 下方的 view
                View viewUnder = findViewUnder(event.getX(), event.getY());
                if (viewUnder != null && viewUnder != draggedView) {
                    int newPosition = indexOfChild(viewUnder);
                    // 交换两个 view 的位置
                    swapViews(draggedViewPosition, newPosition);
                }
                // 重新布局
                requestLayout();
                // 重置拖动状态
                draggedView.setTranslationX(0);
                draggedView.setTranslationY(0);
                draggedView = null;
                draggedViewPosition = -1;
                break;
        }
        return true;
    }

    private View findViewUnder(float x, float y) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == VISIBLE
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
}
