package fan.akua.day6.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fan.akua.day6.R;
import fan.akua.day6.adapters.TestAdapter;
import fan.akua.day6.bean.ImgBean;

public class BRVAHActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brvah);
        RecyclerView rv = findViewById(R.id.rv);
        TestAdapter adapter=new TestAdapter(List.of(
                new ImgBean("https://www.dmoe.cc/random.php"),
                new ImgBean("https://www.dmoe.cc/random.php"),
                new ImgBean("https://www.dmoe.cc/random.php"),
                new ImgBean("https://www.dmoe.cc/random.php"),
                new ImgBean("https://www.dmoe.cc/random.php"),
                new ImgBean("https://www.dmoe.cc/random.php"),
                new ImgBean("https://www.dmoe.cc/random.php"),
                new ImgBean("https://www.dmoe.cc/random.php"),
                new ImgBean("https://www.dmoe.cc/random.php"),
                new ImgBean("https://www.dmoe.cc/random.php")
        ));
        rv.setAdapter(adapter);
    }
}
