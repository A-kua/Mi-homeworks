package fan.akua.day7.activities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day7.databinding.ActivityFrameBinding;

public class FrameAnimActivity extends AppCompatActivity {
    private ActivityFrameBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFrameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.start.setOnClickListener(v -> {
            AnimationDrawable background = (AnimationDrawable) binding.img.getBackground();
            background.start();
        });
        binding.stop.setOnClickListener(v -> {
            AnimationDrawable background = (AnimationDrawable) binding.img.getBackground();
            background.stop();
        });

    }
}
