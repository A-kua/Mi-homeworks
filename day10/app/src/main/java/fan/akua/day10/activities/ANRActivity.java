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


//InputDispatching Timeout：5秒内无法响应屏幕触摸事件或键盘输入事件

//BroadcastQueue Timeout ：在执行前台广播（BroadcastReceiver）的onReceive()函数时10秒没有处理完成，后台为60秒。
//Service Timeout ：前台服务20秒内，后台服务在200秒内没有执行完毕。
//ContentProvider Timeout ：ContentProvider的publish在10s内没进行完。

// /data/anr/traces.txt
//触发ANR的过程可分为三个步骤: 埋炸弹, 拆炸弹, 引爆炸弹。
//input的超时检测机制
// 二次点击就会触发无响应