package fan.akua.day3.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import fan.akua.day3.R;
import fan.akua.day3.fragments.AFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            return;
        }
        AFragment aFragment = AFragment.newInstance("test");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.add(R.id.dynamicContainer, aFragment);
        transaction.commit();


//        aFragment 会替换目前在 R.id.fragment_container ID 所标识的布局容器中的任何片段（如有）。
//        通过调用 addToBackStack()，可以将替换事务保存到返回栈，以便用户能够通过按返回按钮撤消事务并回退到上一片段。

//        如果向事务添加多个更改（如又一个 add() 或 remove()），并调用 addToBackStack()，
//        则调用 commit() 前应用的所有更改都将作为单一事务添加到返回栈，并且返回按钮会将它们一并撤消。
    }
}
