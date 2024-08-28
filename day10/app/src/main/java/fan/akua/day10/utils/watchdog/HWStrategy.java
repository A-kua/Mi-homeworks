package fan.akua.day10.utils.watchdog;

import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

public class HWStrategy implements CheckStrategy {
    private static final int DEFAULT_ANR_TIMEOUT = 1_000;
    private static final int DEFAULT_ANR_COUNT = 5;

    private final int _timeoutInterval, _threshold;
    private final Runnable checkRunnable = new Runnable() {
        @Override
        public void run() {
            _tickCount.set(0);
            _reported = false;
        }
    };

    private final AtomicInteger _tickCount = new AtomicInteger();
    // 防止报ANR多次。
    private volatile boolean _reported;


    public HWStrategy() {
        this(DEFAULT_ANR_TIMEOUT, DEFAULT_ANR_COUNT);
    }

    public HWStrategy(int threshold) {
        this(DEFAULT_ANR_TIMEOUT, threshold);
    }

    public HWStrategy(int timeoutInterval, int threshold) {
        _timeoutInterval = timeoutInterval;
        _threshold = threshold;
    }

    @Override
    public boolean needPost() {
        return _tickCount.get() < _threshold;
    }

    @Override
    public long sleepTime() {
        return _timeoutInterval;
    }

    @Override
    public boolean isANR() {
        boolean isANR = _tickCount.get() >= _threshold && !_reported;
        if (isANR)
            _reported = true;
        return isANR;
    }

    @Override
    public Runnable tickMessage() {
        _tickCount.incrementAndGet();
        return checkRunnable;
    }
}
