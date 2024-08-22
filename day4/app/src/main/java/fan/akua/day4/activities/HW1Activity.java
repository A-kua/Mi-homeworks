package fan.akua.day4.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day4.R;
import fan.akua.day4.fragments.HW1Fragment;

public class HW1Activity extends AppCompatActivity {
    private final HW1Fragment fragment = HW1Fragment.newInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw1);
        if (savedInstanceState != null) return;
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.container, fragment)
                .commit();
    }
}
