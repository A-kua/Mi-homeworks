package fan.akua.day7.activities;

import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day7.R;
import fan.akua.day7.databinding.ActivityAlphaBinding;

public class AlphaActivity extends AppCompatActivity {
    protected ActivityAlphaBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlphaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startAlpha.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(AlphaActivity.this, R.anim.anim_alpha);
            animation.setFillAfter(binding.swit.isChecked());
            binding.img.startAnimation(animation);
        });

        binding.startComplex.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(AlphaActivity.this, R.anim.anim_complex);
            animation.setFillAfter(binding.swit.isChecked());
            binding.img.startAnimation(animation);
        });

        binding.startScale.setOnClickListener(v -> {
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    1f, 1.2f,
                    1f, 1.2f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(1000);
            binding.img.startAnimation(scaleAnimation);
        });

        binding.startSet.setOnClickListener(v -> {
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    1f, 1.2f,
                    1f, 1.2f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
            );
            scaleAnimation.setDuration(1000);
            scaleAnimation.setRepeatCount(4);

            RotateAnimation rotateAnimation = new RotateAnimation(
                    0f, 360f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
            );
            rotateAnimation.setDuration(1000);

            AlphaAnimation alphaAnimation = new AlphaAnimation(
                    1f, 0f
            );
            alphaAnimation.setDuration(1000);
            alphaAnimation.setRepeatMode(Animation.REVERSE);
            alphaAnimation.setRepeatCount(5);

            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(rotateAnimation);
            animationSet.addAnimation(alphaAnimation);

            animationSet.setStartOffset(0);

            binding.img.startAnimation(animationSet);
        });

        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        animation.setDuration(1000);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setFillAfter(true);
        binding.img.startAnimation(animation);

        Animation scaleAnim = AnimationUtils.loadAnimation(AlphaActivity.this, R.anim.anim_set);
        binding.getRoot().startAnimation(scaleAnim);
    }
}
