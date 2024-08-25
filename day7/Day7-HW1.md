## Day7-HW1

相关的文件如下：
1. [HW1Activity.java](app/src/main/java/fan/akua/day7/activities/HW1Activity.java)

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

很简单，没啥说的。需要注意AnimationSet设置重复是无效的

```java
    public void animClick(View view) {
        AnimationSet animationSet = getAnimationSet();

        view.startAnimation(animationSet);
    }

    private static @NonNull AnimationSet getAnimationSet() {
        AnimationSet animationSet = new AnimationSet(true);

        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.5f, 1.0f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(3);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            private int repeatCount;

            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("Animation", "animation start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("Animation", "animation end");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d("Animation", "animation repeat" + repeatCount++);
            }
        });

        RotateAnimation rotateAnimation = new RotateAnimation(
                0, -720,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(3);
        rotateAnimation.setRepeatMode(Animation.REVERSE);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.8f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setRepeatCount(3);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }
```

### 运行效果如下

[视频无法播放请点击我](vx_images/20240825_170525.mp4)

<div>
    <video src="vx_images/20240825_170525.mp4"></video>
</div>

