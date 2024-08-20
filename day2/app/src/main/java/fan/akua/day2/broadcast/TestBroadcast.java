package fan.akua.day2.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class TestBroadcast extends BroadcastReceiver implements Runnable {
    private static final String TAG = TestBroadcast.class.getName();
    private PendingResult pendingResult;
    private ConnectivityManager connectivityManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Log.d(TAG, "onReceive: " + intent);
        pendingResult = goAsync();
        new Thread(this).start();
    }

    @Override
    public void run() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            Log.d(TAG, "NetworkInfo NULL");
            return;
        }
        Log.d(TAG, "State: " + networkInfo.isAvailable());

        Log.d(TAG, "Type: " + networkInfo.getType());
        Log.d(TAG, "广播异步线程处理结束");
        pendingResult.finish();
    }
}
