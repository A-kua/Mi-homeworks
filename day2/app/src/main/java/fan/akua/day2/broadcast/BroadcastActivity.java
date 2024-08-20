package fan.akua.day2.broadcast;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import fan.akua.day2.databinding.ActivityBroadcastBinding;

public class BroadcastActivity extends Activity {
    private static final String TAG = BroadcastActivity.class.getName();

    protected ActivityBroadcastBinding binding;
    protected final TestBroadcast testBroadcast = new TestBroadcast();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBroadcastBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.reg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
            @Override
            public void onClick(View v) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                registerReceiver(testBroadcast, intentFilter);
            }
        });
        binding.send.setOnClickListener(v -> {
            Intent intent=new Intent("fan.akua.day2.broadcast");
            intent.setComponent(new ComponentName(this,TestBroadcast2.class));
            sendBroadcast(intent);
            Log.d(TAG, "发送了一个静态广播");
        });
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(testBroadcast);
        super.onDestroy();
    }
}
