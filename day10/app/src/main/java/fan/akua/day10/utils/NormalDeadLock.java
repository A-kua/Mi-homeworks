package fan.akua.day10.utils;

import android.util.Log;

public class NormalDeadLock {

    private static final Object valueFirst = new Object();
    private static final Object valueSecond = new Object();

    private static void firstToSecond() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (valueFirst) {
            Log.e(threadName, "get first");
            Thread.sleep(0);
            synchronized (valueSecond) {
                Log.e(threadName, "get second");
            }
        }
    }

    private static void SecondToFirst() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        synchronized (valueSecond) {
            Log.e(threadName, "get second");
            Thread.sleep(0);
            synchronized (valueFirst) {
                Log.e(threadName, "get first");
            }
        }
    }

    private static class TestThread extends Thread {
        private final String name;

        public TestThread(String name) {
            this.name = name;
        }

        public void run() {
            Thread.currentThread().setName(name);
            while (true) {
                try {
                    SecondToFirst();
                } catch (InterruptedException ignored) {

                }
                Log.e("Thread1", "end");
            }
        }
    }

    public static void startDeadLockThreads() {
        TestThread test1Thread = new TestThread("Thread1");
        test1Thread.start();
        new Thread(() -> {
            while (true) {
                try {
                    firstToSecond();
                } catch (InterruptedException ignored) {

                }
                Log.e("Thread2", "end");
            }
        }).start();
    }
}