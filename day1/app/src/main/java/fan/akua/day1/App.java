package fan.akua.day1;

import android.app.Application;
import android.widget.Toast;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG_MODE) {
            Toast.makeText(this, "debug mode!", Toast.LENGTH_SHORT).show();
        }
    }
}
