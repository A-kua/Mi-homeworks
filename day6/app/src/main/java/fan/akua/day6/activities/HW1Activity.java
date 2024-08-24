package fan.akua.day6.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fan.akua.day6.R;
import fan.akua.day6.adapters.VPAdapter;

public class HW1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw1);
        ViewPager viewPager = findViewById(R.id.vp);
        viewPager.setAdapter(new VPAdapter(getSupportFragmentManager()));
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        bottomNavigationView.setSelectedItemId(R.id.navigation_item1);
                        return;
                    }
                    case 1: {
                        bottomNavigationView.setSelectedItemId(R.id.navigation_item2);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (R.id.navigation_item1 == itemId) {
                viewPager.setCurrentItem(0);
                return true;
            } else if (R.id.navigation_item2 == itemId) {
                viewPager.setCurrentItem(1);
                return true;
            }
            return false;
        });
    }
}
