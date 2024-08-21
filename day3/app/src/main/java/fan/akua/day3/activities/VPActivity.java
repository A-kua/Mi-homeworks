package fan.akua.day3.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import fan.akua.day3.R;
import fan.akua.day3.adapters.VPAdapter;
import fan.akua.day3.base.BaseActivity;

public class VPActivity extends BaseActivity {
    private ViewPager viewPager;

    @Override
    protected String getLogTag() {
        return "VPActivity";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
        viewPager = findViewById(R.id.vp);

        viewPager.setAdapter(new VPAdapter(getSupportFragmentManager()));
    }
}
