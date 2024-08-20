package fan.akua.day2.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import fan.akua.day2.IMyAidlInterface;
import fan.akua.day2.aidl.AIDLService;
import fan.akua.day2.databinding.ActivityAidlBinding;

public class AIDLActivity extends Activity {
    protected ActivityAidlBinding binding;
    protected IMyAidlInterface aidlInterface;
    protected final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAidlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bind.setOnClickListener(v -> {
            Intent intent = new Intent(AIDLActivity.this, AIDLService.class);
            bindService(intent, connection, BIND_AUTO_CREATE | BIND_NOT_FOREGROUND);
            binding.tv.setText("绑定成功");
        });
        binding.sum.setOnClickListener(v -> {
            try {
                int a = Integer.parseInt(binding.numA.getText().toString());
                int b = Integer.parseInt(binding.numB.getText().toString());
                if (aidlInterface != null) {
                    int i = aidlInterface.basicTypes(a, b);
                    binding.tv.setText("得到结果：" + i);
                } else {
                    binding.tv.setText("先绑定一下");
                }
            } catch (Exception e) {
                binding.tv.setText("输入有误。" + e.getLocalizedMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }
}
