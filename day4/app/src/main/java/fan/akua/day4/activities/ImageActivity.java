package fan.akua.day4.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day4.R;

public class ImageActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        ImageView imageView = findViewById(R.id.img);
        SeekBar seekBar = findViewById(R.id.seek);
        TextView tv = findViewById(R.id.tv);
        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(v -> seekBar.setProgress(0));
        seekBar.setMax(360);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageView.setPivotX(imageView.getWidth() / 2);
                imageView.setPivotY(imageView.getHeight() / 2);
                imageView.setRotation(progress);
                tv.setText("当前角度：" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
