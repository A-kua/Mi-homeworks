package fan.akua.day1;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import fan.akua.day1.databinding.ActivityHelloBinding;

public class MainActivity extends Activity {
    protected ActivityHelloBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelloBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
