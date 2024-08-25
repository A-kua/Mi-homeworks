## Day7-HW2

相关的文件如下：
1. [HW2Activity.java](app/src/main/java/fan/akua/day7/activities/HW2Activity.java)

### 编写xml

偷个懒，直接用onClik了

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center">

    <ImageView
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:onClick="animClick"
        android:src="@drawable/anim_frame" />
</LinearLayout>
```

### 编写代码

很简单，rotate先执行，然后alpha和tran同时执行

```java
    public void animClick(View view) {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotationX", 0, 360f);
        ObjectAnimator tran = ObjectAnimator.ofFloat(view, "translationX", 0, 120f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 0.5f);

        rotate.setDuration(1000);
        tran.setDuration(1200);
        alpha.setDuration(500);
        // float估值器
        rotate.setEvaluator((TypeEvaluator<Float>) (fraction, startValue, endValue) -> {
            float startFloat = startValue.floatValue();
            return startFloat + fraction * (endValue.floatValue() - startFloat);
        });
        // 减速插值器
        tran.setInterpolator(input -> 1.0f - (1.0f - input) * (1.0f - input));
        // 1.1次幂插值器
        alpha.setInterpolator(input -> (float) Math.pow((double) input / 0.5f, 1.1d));

        AnimatorSet togetherSet = new AnimatorSet();
        togetherSet.playTogether(tran, alpha);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(rotate, togetherSet);

        animatorSet.start();
    }
```

### 运行效果如下

[视频无法播放请点击我](vx_images/Screen_recording_20240825_163321.mp4)

<div>
    <video src="vx_images/Screen_recording_20240825_163321.mp4"></video>
</div>

