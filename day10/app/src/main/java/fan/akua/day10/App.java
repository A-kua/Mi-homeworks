package fan.akua.day10;

import android.app.Application;
import android.util.Log;

import com.github.anrwatchdog.ANRWatchDog;

import java.util.function.Consumer;

import fan.akua.day10.utils.watchdog.ANRError;
import fan.akua.day10.utils.watchdog.AkuaWatchDog;
import fan.akua.day10.utils.watchdog.HWStrategy;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new ANRWatchDog(5_000)
                .setANRListener(error -> Log.e("!!!ANR_Ori!!!", "ANRWatchDog"))
                .start();
        new AkuaWatchDog()
                .setCallBack(new Consumer<ANRError>() {
                    @Override
                    public void accept(ANRError anrError) {
                        Log.e("!!!ANR_Akua!!!", "ANRWatchDog");
                    }
                }).start();
    }
}
