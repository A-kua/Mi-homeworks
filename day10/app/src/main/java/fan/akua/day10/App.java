package fan.akua.day10;

import android.app.Application;
import android.util.Log;

import java.util.function.Consumer;

import fan.akua.day10.utils.watchdog.ANRError;
import fan.akua.day10.utils.watchdog.AkuaWatchDog;
import fan.akua.day10.utils.watchdog.HWStrategy;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new AkuaWatchDog(new HWStrategy())
                .setCallBack(new Consumer<ANRError>() {
                    @Override
                    public void accept(ANRError anrError) {
                        Log.e("!!!ANR!!!", "AkuaANRWatchDog发现了ANR");
                    }
                }).start();
    }
}
