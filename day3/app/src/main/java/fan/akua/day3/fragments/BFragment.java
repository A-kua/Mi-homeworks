package fan.akua.day3.fragments;

import androidx.fragment.app.Fragment;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseFragment;

public class BFragment extends BaseFragment {
    private static final String TAG = BFragment.class.getName();

    public BFragment() {
        super(R.layout.fragment_b);
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }
}
