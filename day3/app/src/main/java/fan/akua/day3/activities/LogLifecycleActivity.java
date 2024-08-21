package fan.akua.day3.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseActivity;
import fan.akua.day3.fragments.AFragment;
import fan.akua.day3.fragments.BFragment;

public class LogLifecycleActivity extends BaseActivity {
    private static final String TAG = LogLifecycleActivity.class.getName();

    @Override
    protected String getLogTag() {
        return TAG;
    }

    private final AFragment aFragment = AFragment.newInstance("AFragment");
    private final AFragment replaceFragment = AFragment.newInstance("replaceFragment");
    private final BFragment bFragment = new BFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        findViewById(R.id.add).setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.container1, aFragment)
                .add(R.id.container2, bFragment)
                .commit());
        findViewById(R.id.hide).setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .hide(aFragment)
                .commit());
        findViewById(R.id.show).setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .show(aFragment)
                .commit());
        findViewById(R.id.replace).setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.container1, replaceFragment)
                .commit());
    }
}
