package fan.akua.day7.activities;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day7.R;
import fan.akua.day7.databinding.ActivityAnimatorBinding;
import fan.akua.day7.utils.AnimatorUtils;

public class AnimatorActivity extends AppCompatActivity {
    private ActivityAnimatorBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnimatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startFly.setOnClickListener(v -> AnimatorUtils.introAnimate(binding.img, 15.0F, 170));
        binding.startTran.setOnClickListener(v -> {
            Animator animator = AnimatorInflater.loadAnimator(AnimatorActivity.this, R.animator.animator_rotation_x);
            animator.setTarget(binding.img);
            animator.start();
        });
        binding.startTranCode.setOnClickListener(v -> {
            ObjectAnimator animator= ObjectAnimator.ofFloat(binding.img, "rotationX", 0f, 360f);
            animator.setDuration(1000);
            animator.start();
        });
        binding.startTranTran.setOnClickListener(v -> {
            ObjectAnimator animator_rotate = ObjectAnimator.ofFloat(binding.img, "rotationX", 0f, 360f);
            ObjectAnimator animator_tran = ObjectAnimator.ofFloat(binding.img, "translationX", 0f, 100);

            animator_rotate.setDuration(1000);
            animator_tran.setDuration(1000);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(animator_rotate, animator_tran);

            animatorSet.start();
        });
        binding.startTranTran2.setOnClickListener(v -> {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100f);
            valueAnimator.addUpdateListener(animation -> {
                binding.img.setRotationX((Float) animation.getAnimatedValue() * 3.6f);
                binding.img.setTranslationX((Float) animation.getAnimatedValue());
//                binding.img.invalidate();
            });
            valueAnimator.setDuration(1000);
            valueAnimator.start();
        });
    }
}
