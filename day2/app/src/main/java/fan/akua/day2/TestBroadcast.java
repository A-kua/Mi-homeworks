package fan.akua.day2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TestBroadcast extends BroadcastReceiver {
    private static final String TAG = TestBroadcast.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + intent);
    }
}
