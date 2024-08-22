package fan.akua.day4.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.Random;

import fan.akua.day4.R;
import fan.akua.day4.adapter.VPAdapter;

public class VPActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
        ViewPager vp = findViewById(R.id.vp);

        int[] colors = new int[]{Color.RED, Color.BLACK, Color.BLUE, Color.GRAY, Color.GREEN, Color.CYAN};
        vp.setAdapter(new VPAdapter(List.of(
                new View(this) {
                    {
                        post(() -> {
                            ViewGroup.LayoutParams layoutParams = new androidx.viewpager.widget.ViewPager.LayoutParams();
                            setLayoutParams(layoutParams);
                            setBackgroundColor(colors[Math.abs(new Random().nextInt() % (colors.length - 1))]);
                        });
                    }
                },
                new View(this) {
                    {
                        post(() -> {
                            ViewGroup.LayoutParams layoutParams = new androidx.viewpager.widget.ViewPager.LayoutParams();
                            setLayoutParams(layoutParams);
                            setBackgroundColor(colors[Math.abs(new Random().nextInt() % (colors.length - 1))]);
                        });
                    }
                },
                new View(this) {
                    {
                        post(() -> {
                            ViewGroup.LayoutParams layoutParams = new androidx.viewpager.widget.ViewPager.LayoutParams();
                            setLayoutParams(layoutParams);
                            setBackgroundColor(colors[Math.abs(new Random().nextInt() % (colors.length - 1))]);
                        });
                    }
                },
                new View(this) {
                    {
                        post(() -> {
                            ViewGroup.LayoutParams layoutParams = new androidx.viewpager.widget.ViewPager.LayoutParams();
                            setLayoutParams(layoutParams);
                            setBackgroundColor(colors[Math.abs(new Random().nextInt() % (colors.length - 1))]);
                        });
                    }
                }
        )));
    }
}
