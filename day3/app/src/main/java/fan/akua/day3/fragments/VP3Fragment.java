package fan.akua.day3.fragments;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseFragment;

public class VP3Fragment extends BaseFragment {
    public static VP3Fragment newInstance() {
        return new VP3Fragment();
    }
    private VP3Fragment() {
        super(R.layout.fragment_vp3);
    }

    @Override
    protected String getLogTag() {
        return "VP3Fragment";
    }
}
