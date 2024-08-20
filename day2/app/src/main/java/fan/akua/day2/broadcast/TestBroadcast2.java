package fan.akua.day2.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TestBroadcast2 extends BroadcastReceiver {
    private static final String TAG = TestBroadcast2.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "静态注册收到消息了哦~");
    }
}
