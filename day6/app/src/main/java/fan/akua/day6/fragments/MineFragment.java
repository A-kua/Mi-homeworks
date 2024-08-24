package fan.akua.day6.fragments;

import androidx.fragment.app.Fragment;

import fan.akua.day6.R;

public class MineFragment extends Fragment {
    public static MineFragment newInstance() {
        return new MineFragment();
    }

    private MineFragment() {
        super(R.layout.fragment_mine);
    }
}
