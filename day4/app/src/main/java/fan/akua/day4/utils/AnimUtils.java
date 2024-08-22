package fan.akua.day4.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.util.Property;
import android.view.View;

public class AnimUtils {
    public static void continueOutToRight(View view, float paramFloat, int paramInt) {
        Property localProperty1 = View.TRANSLATION_Y;
        float[] arrayOfFloat1 = new float[2];
        arrayOfFloat1[0] = (-paramFloat * view.getResources().getDisplayMetrics().density);
        arrayOfFloat1[1] = (-view.getResources().getDisplayMetrics().heightPixels);
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(view, localProperty1, arrayOfFloat1).setDuration(900L);
        localObjectAnimator1.setInterpolator(new ExpoIn());
        localObjectAnimator1.setStartDelay(paramInt + 0);
        localObjectAnimator1.start();
        Property localProperty2 = View.TRANSLATION_X;
        float[] arrayOfFloat2 = new float[2];
        arrayOfFloat2[0] = view.getTranslationX();
        arrayOfFloat2[1] = view.getResources().getDisplayMetrics().widthPixels;
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(view, localProperty2, arrayOfFloat2).setDuration(900L);
        localObjectAnimator2.setInterpolator(new ExpoIn());
        localObjectAnimator2.setStartDelay(paramInt + 0);
        localObjectAnimator2.start();

    }
    public static float getDistanceToCenter(View paramView) {
        float f = paramView.getTop() + paramView.getHeight() / 2.0F;
        return ((View) paramView.getParent()).getHeight() / 2 + paramView.getHeight() / 2.0F - f;
    }

    public static float getDistanceToCenterX(View paramView) {
        float f = paramView.getLeft() + paramView.getWidth() / 2.0F;
        return ((View) paramView.getParent()).getWidth() / 2 + paramView.getWidth() / 2.0F - f;
    }
    public static ObjectAnimator introAnimate(View paramDepthLayout, float paramFloat1, float paramFloat2, long paramLong, int paramInt) {
        paramDepthLayout.setPivotY(getDistanceToCenter(paramDepthLayout));
        paramDepthLayout.setPivotX(getDistanceToCenterX(paramDepthLayout));
        paramDepthLayout.setCameraDistance(10000.0F * paramDepthLayout.getResources().getDisplayMetrics().density);
        Property localProperty1 = View.TRANSLATION_Y;
        float[] arrayOfFloat1 = new float[2];
        arrayOfFloat1[0] = paramDepthLayout.getResources().getDisplayMetrics().heightPixels;
        arrayOfFloat1[1] = (-paramFloat1 * paramDepthLayout.getResources().getDisplayMetrics().density);
        ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty1, arrayOfFloat1).setDuration(800L);
        localObjectAnimator1.setInterpolator(new ExpoOut());
        localObjectAnimator1.setStartDelay(paramInt + 700);
        localObjectAnimator1.start();
        paramDepthLayout.setTranslationY(paramDepthLayout.getResources().getDisplayMetrics().heightPixels);
        Property localProperty2 = View.TRANSLATION_X;
        float[] arrayOfFloat2 = new float[2];
        arrayOfFloat2[0] = (-paramDepthLayout.getResources().getDisplayMetrics().widthPixels);
        arrayOfFloat2[1] = 0.0F;
        ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty2, arrayOfFloat2).setDuration(800L);
        localObjectAnimator2.setInterpolator(new ExpoOut());
        localObjectAnimator2.setStartDelay(paramInt + 700);
        localObjectAnimator2.start();
        paramDepthLayout.setTranslationX(-paramDepthLayout.getResources().getDisplayMetrics().widthPixels);
        ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(paramDepthLayout, View.TRANSLATION_Y, new float[]{0.0F}).setDuration(700L);
        localObjectAnimator3.setInterpolator(new BackOut());
        localObjectAnimator3.setStartDelay(1500L);
        localObjectAnimator3.start();
        ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(paramDepthLayout, View.ROTATION_X, new float[]{60.0F, 0.0F}).setDuration(1000L);
        localObjectAnimator4.setInterpolator(new QuintInOut());
        localObjectAnimator4.setStartDelay(paramInt + 1000);
        localObjectAnimator4.start();
        paramDepthLayout.setRotationX(60.0F);
        float[] arrayOfFloat3 = new float[2];
        arrayOfFloat3[0] = (paramFloat2 * paramDepthLayout.getResources().getDisplayMetrics().density);
//        arrayOfFloat3[1] = paramDepthLayout.getCustomShadowElevation();
        arrayOfFloat3[1] = 12;
//        ObjectAnimator localObjectAnimator5 = ObjectAnimator.ofFloat(paramDepthLayout, "CustomShadowElevation", arrayOfFloat3).setDuration(1000L);
//        localObjectAnimator5.setInterpolator(new QuintInOut());
//        localObjectAnimator5.setStartDelay(1000 + paramInt * 2);
//        localObjectAnimator5.start();
//        paramDepthLayout.setCustomShadowElevation(paramFloat2 * paramDepthLayout.getResources().getDisplayMetrics().density);
        Property localProperty3 = View.SCALE_X;
        float[] arrayOfFloat4 = new float[2];
        arrayOfFloat4[0] = 0.5F;
        arrayOfFloat4[1] = paramDepthLayout.getScaleX();
        ObjectAnimator localObjectAnimator6 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty3, arrayOfFloat4).setDuration(1000L);
        localObjectAnimator6.setInterpolator(new CircInOut());
        localObjectAnimator6.setStartDelay(paramInt + 1000);
        localObjectAnimator6.start();
        paramDepthLayout.setScaleX(0.5F);
        Property localProperty4 = View.SCALE_Y;
        float[] arrayOfFloat5 = new float[2];
        arrayOfFloat5[0] = 0.5F;
        arrayOfFloat5[1] = paramDepthLayout.getScaleY();
        ObjectAnimator localObjectAnimator7 = ObjectAnimator.ofFloat(paramDepthLayout, localProperty4, arrayOfFloat5).setDuration(1000L);
        localObjectAnimator7.setInterpolator(new CircInOut());
        localObjectAnimator7.setStartDelay(paramInt + 1000);
        localObjectAnimator7.start();
        paramDepthLayout.setScaleY(0.5F);
        ObjectAnimator localObjectAnimator8 = ObjectAnimator.ofFloat(paramDepthLayout, View.ROTATION, new float[]{-50.0F, 0.0F}).setDuration(1400L);
        localObjectAnimator8.setInterpolator(new QuadInOut());
        localObjectAnimator8.setStartDelay(paramInt + 300);
        localObjectAnimator8.start();
        paramDepthLayout.setRotation(-50.0F);
//        localObjectAnimator8.addListener(getShowStatusBarListener(paramDepthLayout));
        return localObjectAnimator7;
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


    public static class ExpoIn implements TimeInterpolator {
        public float getInterpolation(float paramFloat) {
            if (paramFloat == 0.0F) {
                return 0.0F;
            }
            return (float) Math.pow(2.0D, 10.0F * (paramFloat - 1.0F));
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
