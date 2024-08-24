package fan.akua.day6.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fan.akua.day6.R;
import fan.akua.day6.adapters.TestAdapter;
import fan.akua.day6.adapters.TestAdapter2;
import fan.akua.day6.bean.ImgBean;

public class SwipeRefreshActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiperefresh);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe);
        RecyclerView rv = findViewById(R.id.rv);
        TestAdapter2 adapter = new TestAdapter2(IntStream.range(0, 40).mapToObj(value -> "这是第" + value).collect(Collectors.toList()));
        rv.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                runOnUiThread(() -> {
                    adapter.addData(IntStream.range(20, 30).mapToObj(value -> "新增的" + value).collect(Collectors.toList()));
                    swipeRefreshLayout.setRefreshing(false);
                });
            }).start();
        });
        adapter.getLoadMoreModule().setOnLoadMoreListener(() -> new Thread(() -> {
            try {
                Thread.sleep(2000);
                runOnUiThread(() -> {
                    adapter.addData(IntStream.range(20, 30).mapToObj(value -> "上拉新增的" + value).collect(Collectors.toList()));
                    adapter.getLoadMoreModule().loadMoreComplete();
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start());
    }
}
