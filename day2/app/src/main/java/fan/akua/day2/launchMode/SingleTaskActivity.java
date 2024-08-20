package fan.akua.day2.launchMode;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class SingleTaskActivity extends Activity {
    private static final String TAG = SingleTaskActivity.class.getName();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG,"onNewIntent");
    }
}
