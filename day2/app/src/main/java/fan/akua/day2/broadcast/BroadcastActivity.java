package fan.akua.day2.broadcast;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import fan.akua.day2.databinding.ActivityBroadcastBinding;

public class BroadcastActivity extends Activity {

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
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(testBroadcast);
        super.onDestroy();
    }
}
