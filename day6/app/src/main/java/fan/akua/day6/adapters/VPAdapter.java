package fan.akua.day6.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import fan.akua.day6.fragments.HomeFragment;
import fan.akua.day6.fragments.MineFragment;

public class VPAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragments = List.of(
            HomeFragment.newInstance(),
            MineFragment.newInstance()
    );

    public VPAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}