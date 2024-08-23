package fan.akua.day5.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day5.R;
import fan.akua.day5.utils.UnitUtils;

public class FrameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        Button btn = findViewById(R.id.dynamic);
        btn.setOnClickListener(v -> {
            int px200 = (int) UnitUtils.dp2px(200);
            int px100 = (int) UnitUtils.dp2px(100);

            Log.d("simon", "px" + px100);

            FrameLayout frameLayout = new FrameLayout(FrameActivity.this);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            frameLayout.setLayoutParams(params);

            TextView tv1 = new TextView(FrameActivity.this);
            FrameLayout.LayoutParams tv1Params = new FrameLayout.LayoutParams(px200, px200);
            tv1Params.gravity = Gravity.BOTTOM | Gravity.END;
            tv1.setBackgroundColor(Color.parseColor("#ff0000"));
            tv1.setText("动态底层");
            tv1.setLayoutParams(tv1Params);

            frameLayout.addView(tv1, tv1Params);

            TextView tv2 = new TextView(FrameActivity.this);
            FrameLayout.LayoutParams tv2Params = new FrameLayout.LayoutParams(px100, px100);
            tv2Params.gravity = Gravity.BOTTOM | Gravity.END;
            tv2.setBackgroundColor(Color.parseColor("#00ff00"));
            tv2.setText("动态顶层");
            tv2.setLayoutParams(tv2Params);

            frameLayout.addView(tv2, tv2Params);

            setContentView(frameLayout);
        });
    }
}
