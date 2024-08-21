package fan.akua.day3.fragments;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseFragment;

public class VP2Fragment extends BaseFragment {
    public static VP2Fragment newInstance() {
        return new VP2Fragment();
    }
    private VP2Fragment() {
        super(R.layout.fragment_vp2);
    }

    @Override
    protected String getLogTag() {
        return "VP2Fragment";
    }
}
