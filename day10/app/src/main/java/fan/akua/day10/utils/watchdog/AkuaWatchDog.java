package fan.akua.day10.utils.watchdog;

import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.function.Consumer;

public final class AkuaWatchDog extends Thread {
    private static final CheckStrategy DEFAULT_STRATEGY = new TraditionalStrategy();

    private final Handler _handler = new Handler(Looper.getMainLooper());
    private final CheckStrategy _strategy;
    private Consumer<ANRError> _anrConsumer;
    private Consumer<InterruptedException> _interruptListener;
    private boolean _ignoreDebug;

    public AkuaWatchDog() {
        this(DEFAULT_STRATEGY);
    }

    public AkuaWatchDog(CheckStrategy strategy) {
        _strategy = strategy;
    }

    /**
     * 设置ANR回调
     *
     * @param consumer 回调
     * @return 链式调用
     */
    public AkuaWatchDog setCallBack(Consumer<ANRError> consumer) {
        this._anrConsumer = consumer;
        return this;
    }

    /**
     * 设置WatchDog监听中止的回调
     *
     * @param interruptListener 中断回调
     * @return 链式调用
     */
    public AkuaWatchDog setInterruptListener(Consumer<InterruptedException> interruptListener) {
        this._interruptListener = interruptListener;
        return this;
    }

    /**
     * 设置是否忽略Debug模式
     *
     * @param ignoreDebugger 是否忽略
     * @return 链式调用
     */
    public AkuaWatchDog setIgnoreDebugger(boolean ignoreDebugger) {
        _ignoreDebug = ignoreDebugger;
        return this;
    }

    @Override
    public void run() {
        setName("|Akua-ANR-WatchDog|");
        while (!isInterrupted()) {
            if (_strategy.needPost())
                _handler.post(_strategy.tickMessage());
            try {
                Thread.sleep(_strategy.sleepTime());
            } catch (InterruptedException exception) {
                if (_interruptListener != null)
                    _interruptListener.accept(exception);
            }
            if (_anrConsumer != null && _strategy.isANR()) {
                if (!_ignoreDebug && (Debug.isDebuggerConnected() || Debug.waitingForDebugger())) {
                    Log.w("AkuaWatchDog", "An ANR was detected but ignored because the debugger is connected (you can prevent this with setIgnoreDebugger(true))");
                    continue;
                }
                _anrConsumer.accept(ANRError.NewMainOnly());
            }
        }
    }
}
