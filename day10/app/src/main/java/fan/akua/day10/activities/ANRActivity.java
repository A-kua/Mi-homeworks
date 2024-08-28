package fan.akua.day10.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day10.utils.NormalDeadLock;
import fan.akua.day10.databinding.ActivityMainBinding;

public class ANRActivity extends AppCompatActivity {
    private static final String TAG = ANRActivity.class.getName();
    private final Object lock = new Object();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Thread(() -> {
            while (true)
                synchronized (lock) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        }).start();
        NormalDeadLock.startDeadLockThreads();
    }

    public void anr(View view) throws InterruptedException {
        Log.d(TAG, "onClick start1");
        Thread.sleep(10_000_000);
        Log.d(TAG, "onClick end1");
    }

    public void anr2(View view) {
        Log.d(TAG, "onClick start2");
        synchronized (lock) {
            Log.d(TAG, "onClick end2");
        }
    }
}