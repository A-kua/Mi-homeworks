package fan.akua.day10;

import android.app.Application;

import com.github.anrwatchdog.ANRError;
import com.github.anrwatchdog.ANRWatchDog;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new ANRWatchDog(10000)
                .setANRListener(new ANRWatchDog.ANRListener() {
                    @Override
                    public void onAppNotResponding(ANRError error) {

                    }
                })
                .start();

    }
}
