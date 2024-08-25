package fan.akua.day7.activities;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,0);
        animation.setDuration(1000);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setFillAfter(true);
        binding.img.startAnimation(animation);
    }
}
