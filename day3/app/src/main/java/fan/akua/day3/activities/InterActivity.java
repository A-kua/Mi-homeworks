package fan.akua.day3.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseActivity;
import fan.akua.day3.fragments.CFragment;
import fan.akua.day3.fragments.DFragment;

public class InterActivity extends BaseActivity implements CFragment.BridgeInterface {

    private final CFragment cFragment = CFragment.newInstance(this);
    private final DFragment dFragment = DFragment.newInstance();

    @Override
    protected String getLogTag() {
        return "InterActivity";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter);
        findViewById(R.id.change).setOnClickListener(v -> {
            if (dFragment.isAdded())
                dFragment.changeColor();
            else
                Toast.makeText(InterActivity.this, "先Add一下吧", Toast.LENGTH_SHORT).show();
        });
        if (savedInstanceState != null)
            return;
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.container1, cFragment)
                .commit();
    }

    @Override
    public void onCommunicate() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.container2, dFragment)
                .commit();
    }
}
