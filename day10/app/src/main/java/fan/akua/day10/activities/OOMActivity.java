package fan.akua.day10.activities;

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
    }
}
/*
强引用，宁愿Oom
弱引用，只要gc就回收
软引用，内存不够才回收
虚引用，啥时候都可能回收
 */