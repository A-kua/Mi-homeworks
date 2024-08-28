package fan.akua.day10.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LeakCanaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
/*
在Activity的onDestroy()后，
LeakCanary使用弱引用来跟踪Activity，
结合gc机制判断Activity是否被回收，从而识别潜在的内存泄漏问题
 */
