package fan.akua.day2;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.annotation.Nullable;

import fan.akua.day2.databinding.ActivityControllerBinding;

public class ServiceControllerActivity extends Activity {
    protected ActivityControllerBinding binding;
    protected final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityControllerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.start.setOnClickListener(v -> {
            Intent intent = new Intent(ServiceControllerActivity.this, TestService.class);
            startService(intent);
        });
        binding.stop.setOnClickListener(v -> {
            Intent intent = new Intent(ServiceControllerActivity.this, TestService.class);
            stopService(intent);
        });
        binding.bind.setOnClickListener(v -> {
            Intent intent = new Intent(ServiceControllerActivity.this, TestService.class);
            bindService(intent, serviceConnection, BIND_AUTO_CREATE | BIND_NOT_FOREGROUND);
        });
        binding.unbind.setOnClickListener(v -> {
            unbindService(serviceConnection);
        });
    }
}
