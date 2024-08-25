package fan.akua.day7.activities;

import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day7.R;

public class HW2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw2);
    }

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
}
