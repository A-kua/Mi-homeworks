package fan.akua.day3.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseActivity;
import fan.akua.day3.fragments.AFragment;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getName();
    private final AFragment aFragment = AFragment.newInstance("test");
    private final AFragment bFragment = AFragment.newInstance("replace");

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 防止Activity恢复导致Fragment重复创建。
//        if (savedInstanceState != null) {
//            return;
//        }

        findViewById(R.id.add).setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.add(R.id.dynamicContainer, aFragment);
            transaction.commit();
        });
        findViewById(R.id.show).setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.show(aFragment);
            transaction.commit();
        });
        findViewById(R.id.hide).setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.hide(aFragment);
            transaction.commit();
        });
        findViewById(R.id.replace).setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.dynamicContainer, bFragment);
            transaction.commit();
        });

//        aFragment 会替换目前在 R.id.fragment_container ID 所标识的布局容器中的任何片段（如有）。
//        通过调用 addToBackStack()，可以将替换事务保存到返回栈，以便用户能够通过按返回按钮撤消事务并回退到上一片段。
//
//        如果向事务添加多个更改（如又一个 add() 或 remove()），并调用 addToBackStack()，
//        则调用 commit() 前应用的所有更改都将作为单一事务添加到返回栈，并且返回按钮会将它们一并撤消。
    }

}
