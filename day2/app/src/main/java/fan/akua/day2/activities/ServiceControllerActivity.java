package fan.akua.day2.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import fan.akua.day2.databinding.ActivityAidlBinding;
import fan.akua.day2.databinding.ActivityServiceBinding;
import fan.akua.day2.service.TestService;

public class ServiceControllerActivity extends Activity {
    protected ActivityServiceBinding binding;
    protected Messenger messenger;
    protected final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.start.setOnClickListener(v -> {
            Intent intent = new Intent(ServiceControllerActivity.this, TestService.class);
            startService(intent);
        });
        binding.stop.setOnClickListener(v -> {
            Intent intent = new Intent(ServiceControllerActivity.this, TestService.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            stopService(intent);
        });
        binding.bind.setOnClickListener(v -> {
            Intent intent = new Intent(ServiceControllerActivity.this, TestService.class);
            bindService(intent, serviceConnection, BIND_AUTO_CREATE | BIND_NOT_FOREGROUND);
        });
        binding.unbind.setOnClickListener(v -> {
            unbindService(serviceConnection);
        });
        binding.send.setOnClickListener(v -> {
            String msg = binding.ed.getText().toString();
            Message obtain = Message.obtain(null, TestService.MSG_FROM_CLIENT);
            Bundle bundle = new Bundle();
            bundle.putString(TestService.DATA_KEY, msg);
            obtain.setData(bundle);
            if (messenger == null) {
                binding.tv.setText("需要先bind一下");
                return;
            }
            try {
                messenger.send(obtain);
            } catch (RemoteException e) {
                binding.tv.setText("崩溃 " + e.getLocalizedMessage());
            }
        });
    }
}
