package fan.akua.day3.fragments;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseFragment;

public class AAnimFragment extends BaseFragment {
    public static AAnimFragment newInstance(){
        return new AAnimFragment();
    }
    private AAnimFragment() {
        super(R.layout.fragment_anim1);
    }

    @Override
    protected String getLogTag() {
        return "AAnimFragment";
    }
}
