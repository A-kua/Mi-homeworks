## Day10-HW2

相关的文件如下：

1. [LeakActivity.java](app/src/main/java/fan/akua/day10/activities/LeakActivity.java)

### 场景1

全局工具类使用了生命周期短的Context

工具类代码如下

```java
public class ToastUtil {
    private static volatile ToastUtil toastUtil;

    public static ToastUtil getInstance(Context context) {
        if (toastUtil == null) {
            synchronized (ToastUtil.class) {
                if (toastUtil == null) {
                    toastUtil = new ToastUtil(context);
                }
            }
        }
        return toastUtil;
    }

    private Context context;

    private ToastUtil(Context context) {
        this.context = context;
    }

    /**
     * 展示一个短文本
     *
     * @param text 文本
     */
    public void toastShort(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
```

Activity中使用

```java
    // 全局Context使用Activity
    public void leak1(View view) {
        ToastUtil.getInstance(this).toastShort("生命周期不同步");
        finish();
    }
```

解决方案：换成Application即可，或者context.getApplication()

### 场景2

匿名内部类持有外部引用

实际上课上说的setOnclickListener会内存泄漏是错的，用weakreference测试一下就知道了

Activity引用View，在onDestroy时会断环成链，Listener随着View一起回收。

```java
    public void outMethod() {
        Log.d("simon", "This is a outer method");
    }
    // 匿名内部类持有外部引用
    public void leak2(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10_000_000);
                } catch (InterruptedException ignored) {
                }
                outMethod();
            }
        }).start();
        finish();
    }
```

解决方案：用WeakReference持有外部对象，调用外部对象函数时判断是否存在。

### 场景3

内部类持有外部引用

精通java就是好，可以随便秀

```java
    public void outMethod() {
        Log.d("simon", "This is a outer method");
    }
    // 内部类持有外部引用
    public void leak3(View view) {
        // 哈哈，精通java就是爽
        class R implements Runnable {

            @Override
            public void run() {
                try {
                    Thread.sleep(10_000_000);
                } catch (InterruptedException ignored) {
                }
                outMethod();
            }
        }
        new Thread(new R()).start();
        finish();
    }
```

解决方案：加入static修饰符，同时使用WeakReference引用外部对象，在调用外部对象时判断是否存在。

### 场景4

Timer、Broadcast、EventBus等需要解除注册的组件未解除。

```java
    // Broadcast等忘记解除注册
    public void leak4(View view) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        registerReceiver(broadcastReceiver, intentFilter);
        finish();
    }
```

解决方案：尽量用LifeCycler吧，这样不容易忘记。

### 场景5

Handler的Message在Activity结束时没有清空。

```java
    // Handler在Activity结束时没清空
    public void leak5(View view) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里耗时操作
            }
        },10_000);
        finish();
    }
```

解决方案：在onDestroy周期中执行handler.removeCallbacksAndMessages(null);

### 场景6

WebView在Activity结束时没调用destroy。

```java
    // WebView在Activity结束时没destroy
    public void leak6(View view) {
        binding.webview.loadUrl("http://blog.akua.fan");
        binding.webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        finish();
    }
```

解决方案：在onDestroy中调用webview.destroy()

### 场景7

Bitmap忘记recycle。

```java
    // Bitmap忘记recycle
    public void leak7(View view) {
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.a119010495_p0);
        binding.getRoot().setBackground(new BitmapDrawable(bitmap));
        finish();
    }
```

解决方案：不使用时调用bitmap.recycle()

### 小结

说实话上述的7个场景，都是因为**长生命周期**持有了**短生命周期**。对于安卓的组件，开发者还是尽量使用LifeCycle，可以大幅避免。