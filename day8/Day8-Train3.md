## Day8-Train3

相关的文件如下：
1. [TestViewGroup.java](app/src/main/java/fan/akua/day8/widgets/TestViewGroup.java)

> 纸上得来终觉浅，绝知此事要躬行。

### 编写代码

思路很简单，在Down的时候看看是哪个View，然后Move的时候设置选中View的Translation，Cancel或者UP的时候交换

```java
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
```


### 运行效果如下

[视频无法播放请点击我](vx_images/Screen_recording_20240826_150241.mp4)

<div>
    <video src="vx_images/Screen_recording_20240826_150241.mp4"></video>
</div>
