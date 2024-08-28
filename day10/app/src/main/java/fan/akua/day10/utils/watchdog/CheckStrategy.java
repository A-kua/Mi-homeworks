package fan.akua.day10.utils.watchdog;

public interface CheckStrategy {
    boolean needPost();

    long sleepTime();

    boolean isANR();

    Runnable tickMessage();
}
