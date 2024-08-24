package fan.akua.day6.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import fan.akua.day6.R;
import fan.akua.day6.events.BEvent;
import fan.akua.day6.events.MessageEvent;

public class MainActivity extends AppCompatActivity {
    private int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EventBus.getDefault().register(this);

        findViewById(R.id.btn).setOnClickListener(
                v -> EventBus.getDefault().post(
                        new MessageEvent("https://api.r10086.com/%E5%9B%BE%E5%8C%85webp/%E4%B8%BA%E7%BE%8E%E5%A5%BD%E4%B8%96%E7%95%8C%E7%8C%AE%E4%B8%8A%E7%A5%9D%E7%A6%8F%E6%A8%AA%E5%B1%8F%E7%B3%BB%E5%88%971/wallhaven-q6ree5.webp")
                ));
        findViewById(R.id.jump).setOnClickListener(
                v -> startActivity(new Intent(this, BActivity.class)));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        RequestOptions options = RequestOptions
                .circleCropTransform().placeholder(R.drawable.ic_android_black_24dp);
        Glide.with(this)
                .load(event.getMessage())
                .apply(options)
                .into((ImageView) findViewById(R.id.img))
                .clearOnDetach();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBEvent(BEvent event) {
        count = count + 1;
        TextView tv = findViewById(R.id.count);
        tv.setText("点击了：" + count);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
