package fan.akua.day3.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.LifecycleOwner;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseFragment;

public class DFragment extends BaseFragment {
    public static final String REQUEST_KEY = "FRAGMENT";

    public static DFragment newInstance() {
        return new DFragment();
    }

    /***
     * 请求该Fragment
     * @param fragmentManager 共有的管理器
     * @param lifecycleOwner 请求者
     * @param listener 回调
     */
    public static void request(final FragmentManager fragmentManager, final LifecycleOwner lifecycleOwner,
                               @NonNull final FragmentResultListener listener) {
        fragmentManager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner, listener);
    }

    private DFragment() {
        super(R.layout.fragment_d);
    }

    @Override
    protected String getLogTag() {
        return "DFragment";
    }

    public void changeColor() {
        getView().findViewById(R.id.back).setBackgroundColor(Color.RED);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.resp).setOnClickListener(v -> {
            Bundle bundle=new Bundle();
            bundle.putString(REQUEST_KEY,"我来啦");
            getParentFragmentManager().setFragmentResult(REQUEST_KEY, bundle);
        });
    }
}
