package fan.akua.day3.fragments;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseFragment;

public class VP4Fragment extends BaseFragment {
    public static VP4Fragment newInstance() {
        return new VP4Fragment();
    }

    private VP4Fragment() {
        super(R.layout.fragment_vp4);
    }

    @Override
    protected String getLogTag() {
        return "VP4Fragment";
    }
}
