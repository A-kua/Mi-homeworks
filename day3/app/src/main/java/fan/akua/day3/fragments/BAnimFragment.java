package fan.akua.day3.fragments;

import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;

import androidx.annotation.Nullable;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseFragment;

public class BAnimFragment extends BaseFragment {
    public static BAnimFragment newInstance() {
        return new BAnimFragment();
    }

    private BAnimFragment() {
        super(R.layout.fragment_anim2);
    }

    @Override
    protected String getLogTag() {
        return "BaseFragment";
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(TransitionInflater.from(getContext())
                        .inflateTransition(android.R.transition.move));

    }
}
