package fan.akua.day6.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import fan.akua.day6.R;
import fan.akua.day6.activities.DetailActivity;
import fan.akua.day6.adapters.HWAdapter;
import fan.akua.day6.bean.MultiBean;
import fan.akua.day6.events.BEvent;
import fan.akua.day6.events.StarEvent;

public class HomeFragment extends Fragment {
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private HWAdapter adapter;

    private HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = view.findViewById(R.id.rv);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe);

        adapter = new HWAdapter(new ArrayList<>(List.of(
                MultiBean.createImg("https://i-blog.csdnimg.cn/direct/eb305aceb35042729efe701df47e7cda.png"),
                MultiBean.createTxt("这是一段文本啊"),
                MultiBean.createTxt("好玩的文本啊"),
                MultiBean.createImg("https://i-blog.csdnimg.cn/direct/7f42ead2a055446dadfca27860957d0e.png"),
                MultiBean.createImg("https://i-blog.csdnimg.cn/direct/f3b76e36a04b4d0bb9b894cfd800d24a.png"),
                MultiBean.createTxt("好玩的文本啊啊实打实大苏打说到阿斯顿阿斯顿阿斯顿阿斯顿阿斯顿阿斯顿阿斯顿阿萨大师的"),
                MultiBean.createTxt("好玩的文本啊")
        )));
        rv.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    swipeRefreshLayout.post(() -> {
                        adapter.addData(List.of(
                                MultiBean.createTxt("这是刷新加入的数据哦"),
                                MultiBean.createImg("https://i-blog.csdnimg.cn/direct/2c91efdba10f449882b9e463280cd9f4.png")
                        ));
                        swipeRefreshLayout.setRefreshing(false);
                    });
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStarEvent(StarEvent event) {
        adapter.setData(event.position, event.bean);
        adapter.notifyItemChanged(event.position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
