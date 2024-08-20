package fan.akua.day2.launchMode;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class DefaultActivity extends Activity {
    private static final String TAG = DefaultActivity.class.getName();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG,"onNewIntent");
    }
}
