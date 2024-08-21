package fan.akua.day3.fragments;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseFragment;

public class VP1Fragment extends BaseFragment {
    public static VP1Fragment newInstance() {
        return new VP1Fragment();
    }

    private VP1Fragment() {
        super(R.layout.fragment_vp1);
    }

    @Override
    protected String getLogTag() {
        return "VP1Fragment";
    }
}
