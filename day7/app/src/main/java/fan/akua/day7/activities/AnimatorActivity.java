package fan.akua.day7.activities;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
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

        binding.startFly.setOnClickListener(v -> AnimatorUtils.introAnimate(binding.img, 15.0F,  170));
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
    }
}
