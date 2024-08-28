package fan.akua.day10.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day10.R;
import fan.akua.day10.databinding.ActivityBBinding;
import fan.akua.day10.utils.ToastUtil;

public class LeakActivity extends AppCompatActivity {
    private ActivityBBinding binding;

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    private final Handler handler=new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void outMethod() {
        Log.d("simon", "This is a outer method");
    }

    // 全局Context使用Activity
    public void leak1(View view) {
        ToastUtil.getInstance(this).toastShort("生命周期不同步");
        finish();
    }

    // 匿名内部类持有外部引用
    public void leak2(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10_000_000);
                } catch (InterruptedException ignored) {
                }
                outMethod();
            }
        }).start();
        finish();
    }

    // 内部类持有外部引用
    public void leak3(View view) {
        // 哈哈，精通java就是爽
        class R implements Runnable {

            @Override
            public void run() {
                try {
                    Thread.sleep(10_000_000);
                } catch (InterruptedException ignored) {
                }
                outMethod();
            }
        }
        new Thread(new R()).start();
        finish();
    }

    // Broadcast等忘记解除注册
    public void leak4(View view) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        registerReceiver(broadcastReceiver, intentFilter);
        finish();
    }

    // Handler在Activity结束时没清空
    public void leak5(View view) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里耗时操作
            }
        },10_000);
        finish();
    }

    // WebView在Activity结束时没destroy
    public void leak6(View view) {
        binding.webview.loadUrl("http://blog.akua.fan");
        binding.webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        finish();
    }
    // Bitmap忘记recycle
    public void leak7(View view) {
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.a119010495_p0);
        binding.getRoot().setBackground(new BitmapDrawable(bitmap));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MessageQueue
        handler.removeCallbacksAndMessages(null);
    }
}
