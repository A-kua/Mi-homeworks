package fan.akua.day6.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

import fan.akua.day6.R;
import fan.akua.day6.bean.MultiBean;
import fan.akua.day6.events.StarEvent;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiBean dat = (MultiBean) getIntent().getSerializableExtra("dat");
        int position = getIntent().getIntExtra("pos", 0);
        setContentView(R.layout.activity_detail);
        ImageView imageView = findViewById(R.id.img);
        TextView tv = findViewById(R.id.tv);
        ToggleButton toggleButton = findViewById(R.id.star);

        toggleButton.setBackgroundResource(dat.isStated() ? R.drawable.baseline_star_24 : R.drawable.baseline_star_border_24);
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            dat.setStated(isChecked);
            EventBus.getDefault().post(new StarEvent(dat, position));
            toggleButton.setBackgroundResource(isChecked ? R.drawable.baseline_star_24 : R.drawable.baseline_star_border_24);
        });

        if (dat.getItemType() == MultiBean.Type.Txt.ordinal()) {
            tv.setText(dat.getTxt());
        } else if (dat.getItemType() == MultiBean.Type.Img.ordinal()) {
            Glide.with(this).load(dat.getImgUrl()).into(imageView);
        } else {
            throw new RuntimeException("what's wrong with you?!");
        }

    }
}
