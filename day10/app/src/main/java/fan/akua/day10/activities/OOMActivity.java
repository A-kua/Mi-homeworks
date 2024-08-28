package fan.akua.day10.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day10.databinding.ActivityOomBinding;

public class OOMActivity extends AppCompatActivity {
    private ActivityOomBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.jump.setOnClickListener(v ->
                startActivity(new Intent(OOMActivity.this, LeakActivity.class))
        );
    }
}
