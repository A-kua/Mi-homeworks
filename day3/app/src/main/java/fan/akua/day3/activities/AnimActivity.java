package fan.akua.day3.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseActivity;
import fan.akua.day3.fragments.AAnimFragment;
import fan.akua.day3.fragments.BAnimFragment;

public class AnimActivity extends BaseActivity {
    @Override
    protected String getLogTag() {
        return "AnimActivity";
    }

    private final AAnimFragment aAnimFragment = AAnimFragment.newInstance();
    private final BAnimFragment bAnimFragment = BAnimFragment.newInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.container, aAnimFragment)
                .commit();
        findViewById(R.id.replace1).setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.container, bAnimFragment)
                .commit());
        findViewById(R.id.replace2).setOnClickListener(v -> {
            View viewById = aAnimFragment.getView().findViewById(R.id.img1);
            getSupportFragmentManager().beginTransaction()
                    .addSharedElement(viewById, "transitionView")
                    .replace(R.id.container, bAnimFragment)
                    .commit();
        });
        findViewById(R.id.reset).setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, aAnimFragment)
                .commit());
    }
}
