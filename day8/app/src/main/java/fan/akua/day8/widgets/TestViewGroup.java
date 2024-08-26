package fan.akua.day8.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
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

        @SuppressLint("DrawAllocation")
        List<View> lineViews = new ArrayList<>();
        int lineWidth = 0;
        int lineHeight = 0;

        int parentWith = 0;
        int parentHeight = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);

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
            for (int j = 0; j < views.size(); j++) {
                View view = views.get(j);

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
}
