package fan.akua.day4.activities;

import static fan.akua.day4.utils.AnimUtils.introAnimate;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day4.R;

public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Mipmapping。为了加快渲染速度和减少图像锯齿
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
//        View viewById = findViewById(R.id.fab);
//
//        viewById.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                introAnimate(viewById, 15.0F, 20.0F, 30L, 170);
//
//            }
//        });

    }

}
