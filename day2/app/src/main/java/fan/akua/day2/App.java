package fan.akua.day2;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class App extends Application {
    private static final String TAG = App.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                TextView tv = new TextView(activity);
                tv.setText(activity.getLocalClassName());
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                tv.setLayoutParams(params);
                tv.setGravity(Gravity.CENTER);
                activity.setContentView(tv);
                Log.d(TAG, "onCreate: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                Log.d(TAG, "onStart: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                Log.d(TAG, "onResume: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                Log.d(TAG, "onPause: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                Log.d(TAG, "onStop: " + activity.getLocalClassName());
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                Log.d(TAG, "onSaveInstanceState: " + activity.getLocalClassName());
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Log.d(TAG, "onDestroy: " + activity.getLocalClassName());
            }
        });
    }
}
