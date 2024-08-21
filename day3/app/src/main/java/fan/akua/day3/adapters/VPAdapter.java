package fan.akua.day3.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import fan.akua.day3.fragments.VP1Fragment;
import fan.akua.day3.fragments.VP2Fragment;
import fan.akua.day3.fragments.VP3Fragment;
import fan.akua.day3.fragments.VP4Fragment;


public class VPAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragments = List.of(
            VP1Fragment.newInstance(),
            VP2Fragment.newInstance(),
            VP3Fragment.newInstance(),
            VP4Fragment.newInstance()
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
