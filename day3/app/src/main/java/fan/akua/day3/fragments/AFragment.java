package fan.akua.day3.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseFragment;

public class AFragment extends BaseFragment {
    public static final String KEY = "key";

    public static AFragment newInstance(String value) {
        AFragment fragment = new AFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY, value);
        fragment.setArguments(bundle);
        return fragment;
    }

    private AFragment() {
        super(R.layout.fragment_a);
    }

    @Override
    protected String getLogTag() {
        return getArguments().getString(KEY);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView viewById = view.findViewById(R.id.text);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String value = arguments.getString(KEY);
            viewById.setText(value);
        }
    }

}
