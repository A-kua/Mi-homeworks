package fan.akua.day10.utils.watchdog;

import android.util.Log;

public class TraditionalStrategy implements CheckStrategy {
    private static final int DEFAULT_ANR_TIMEOUT = 5_000;

    private final int _timeoutInterval;
    private final Runnable checkRunnable = new Runnable() {
        @Override
        public void run() {
            _lastTime = 0;
            _reported = false;
            Log.e("simon", "recv");
        }
    };

    private volatile long _lastTime;
    // 防止报ANR多次。
    private volatile boolean _reported;

    public TraditionalStrategy() {
        this(DEFAULT_ANR_TIMEOUT);
    }

    public TraditionalStrategy(int timeoutInterval) {
        _timeoutInterval = timeoutInterval;
    }

    @Override
    public boolean needPost() {
        return _lastTime == 0;
    }

    @Override
    public long sleepTime() {
        Log.e("simon", "sleep");
        return _timeoutInterval;
    }

    @Override
    public boolean isANR() {
        Log.e("simon", "check " + _lastTime + " " + _reported);
        boolean isANR = _lastTime != 0 && !_reported;
        if (isANR)
            _reported = true;
        return isANR;
    }

    @Override
    public Runnable tickMessage() {
        Log.e("simon", "send");
        _lastTime = System.nanoTime();
        return checkRunnable;
    }
}
