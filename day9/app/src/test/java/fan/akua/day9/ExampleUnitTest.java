package fan.akua.day9;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test_thread() throws InterruptedException {
        ThreadLocal<String> tl = new ThreadLocal<>();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                tl.set("Thread1");
                System.out.println("thread1" + tl.get());
            }
        });
        System.out.println(tl.get());
        thread1.start();
        Thread.sleep(100);
        System.out.println(tl.get());
    }
}