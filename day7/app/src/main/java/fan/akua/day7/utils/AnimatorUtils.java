package fan.akua.day7.utils;


import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.util.Property;
import android.view.View;

public class AnimatorUtils {

    public static float getDistanceToCenter(View paramView) {
        float f = paramView.getTop() + paramView.getHeight() / 2.0F;
        return (float) ((View) paramView.getParent()).getHeight() / 2 + paramView.getHeight() / 2.0F - f;
    }

    public static float getDistanceToCenterX(View paramView) {
        float f = paramView.getLeft() + paramView.getWidth() / 2.0F;
        return (float) ((View) paramView.getParent()).getWidth() / 2 + paramView.getWidth() / 2.0F - f;
    }

    public static void introAnimate(View targetLayout, float translationDis, int duration) {
        targetLayout.setPivotY(getDistanceToCenter(targetLayout));
        targetLayout.setPivotX(getDistanceToCenterX(targetLayout));
        targetLayout.setCameraDistance(10000.0F * targetLayout.getResources().getDisplayMetrics().density);
        Property<View, Float> localProperty1 = View.TRANSLATION_Y;
        float[] arrayOfFloat1 = new float[2];
        arrayOfFloat1[0] = targetLayout.getResources().getDisplayMetrics().heightPixels;
        arrayOfFloat1[1] = (-translationDis * targetLayout.getResources().getDisplayMetrics().density);
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(targetLayout, localProperty1, arrayOfFloat1).setDuration(800L);
        localObjectAnimator1.setInterpolator(new ExpoOut());
        localObjectAnimator1.setStartDelay(duration + 700);
        localObjectAnimator1.start();

        targetLayout.setTranslationY(targetLayout.getResources().getDisplayMetrics().heightPixels);
        Property<View, Float> localProperty2 = View.TRANSLATION_X;
        float[] arrayOfFloat2 = new float[2];
        arrayOfFloat2[0] = (-targetLayout.getResources().getDisplayMetrics().widthPixels);
        arrayOfFloat2[1] = 0.0F;
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(targetLayout, localProperty2, arrayOfFloat2).setDuration(800L);
        localObjectAnimator2.setInterpolator(new ExpoOut());
        localObjectAnimator2.setStartDelay(duration + 700);
        localObjectAnimator2.start();

        targetLayout.setTranslationX(-targetLayout.getResources().getDisplayMetrics().widthPixels);
        ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(targetLayout, View.TRANSLATION_Y, 0.0F).setDuration(700L);
        localObjectAnimator3.setInterpolator(new BackOut());
        localObjectAnimator3.setStartDelay(1500L);
        localObjectAnimator3.start();

        ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(targetLayout, View.ROTATION_X, 60.0F, 0.0F).setDuration(1000L);
        localObjectAnimator4.setInterpolator(new QuintInOut());
        localObjectAnimator4.setStartDelay(duration + 1000);
        localObjectAnimator4.start();

        targetLayout.setRotationX(60.0F);
        Property<View, Float> localProperty3 = View.SCALE_X;
        float[] arrayOfFloat4 = new float[2];
        arrayOfFloat4[0] = 0.5F;
        arrayOfFloat4[1] = targetLayout.getScaleX();
        ObjectAnimator localObjectAnimator6 = ObjectAnimator.ofFloat(targetLayout, localProperty3, arrayOfFloat4).setDuration(1000L);
        localObjectAnimator6.setInterpolator(new CircInOut());
        localObjectAnimator6.setStartDelay(duration + 1000);
        localObjectAnimator6.start();

        targetLayout.setScaleX(0.5F);
        Property<View, Float> localProperty4 = View.SCALE_Y;
        float[] arrayOfFloat5 = new float[2];
        arrayOfFloat5[0] = 0.5F;
        arrayOfFloat5[1] = targetLayout.getScaleY();
        ObjectAnimator localObjectAnimator7 = ObjectAnimator.ofFloat(targetLayout, localProperty4, arrayOfFloat5).setDuration(1000L);
        localObjectAnimator7.setInterpolator(new CircInOut());
        localObjectAnimator7.setStartDelay(duration + 1000);
        localObjectAnimator7.start();

        targetLayout.setScaleY(0.5F);
        ObjectAnimator localObjectAnimator8 = ObjectAnimator.ofFloat(targetLayout, View.ROTATION, -50.0F, 0.0F).setDuration(1400L);
        localObjectAnimator8.setInterpolator(new QuadInOut());
        localObjectAnimator8.setStartDelay(duration + 300);
        localObjectAnimator8.start();

        targetLayout.setRotation(-50.0F);
    }
    public  static class QuadInOut implements TimeInterpolator {
        public float getInterpolation(float paramFloat) {
            float f1 = paramFloat * 2.0F;
            if (f1 < 1.0F) {
                return f1 * (0.5F * f1);
            }
            float f2 = f1 - 1.0F;
            return -0.5F * (f2 * (f2 - 2.0F) - 1.0F);
        }
    }

    public static class BackOut implements TimeInterpolator {
        protected float param_s = 1.70158F;

        public BackOut amount(float paramFloat) {
            this.param_s = paramFloat;
            return this;
        }

        public float getInterpolation(float paramFloat) {
            float f1 = this.param_s;
            float f2 = paramFloat - 1.0F;
            return 1.0F + f2 * f2 * (f1 + f2 * (f1 + 1.0F));
        }
    }


    public static class QuintInOut implements TimeInterpolator {
        public float getInterpolation(float paramFloat) {
            float f1 = paramFloat * 2.0F;
            if (f1 < 1.0F) {
                return f1 * (f1 * (f1 * (f1 * (0.5F * f1))));
            }
            float f2 = f1 - 2.0F;
            return 0.5F * (2.0F + f2 * (f2 * (f2 * (f2 * f2))));
        }
    }


    public static class CircInOut implements TimeInterpolator {
        public float getInterpolation(float paramFloat) {
            float f1 = paramFloat * 2.0F;
            if (f1 < 1.0F) {
                return -0.5F * ((float) Math.sqrt(1.0F - f1 * f1) - 1.0F);
            }
            float f2 = f1 - 2.0F;
            return 0.5F * (1.0F + (float) Math.sqrt(1.0F - f2 * f2));
        }
    }

    public static class ExpoOut implements TimeInterpolator {
        public float getInterpolation(float paramFloat) {
            if (paramFloat == 1.0F) {
                return 1.0F;
            }
            return 1.0F + -(float) Math.pow(2.0D, -10.0F * paramFloat);
        }
    }
}

