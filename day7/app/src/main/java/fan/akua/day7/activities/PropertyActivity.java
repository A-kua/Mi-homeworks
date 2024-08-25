package fan.akua.day7.activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day7.databinding.ActivityPropertyBinding;

public class PropertyActivity extends AppCompatActivity {
    private ActivityPropertyBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPropertyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(v -> binding.img.animate()
                .rotationX(360f)
                .translationX(100f)
                .setDuration(1000)
                .start());
    }
}
