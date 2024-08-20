package fan.akua.day2;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class SingleInstanceActivity extends Activity {
    private static final String TAG = SingleInstanceActivity.class.getName();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG,"onNewIntent");
    }
}
