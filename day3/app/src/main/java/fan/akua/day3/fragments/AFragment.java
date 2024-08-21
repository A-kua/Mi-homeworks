package fan.akua.day3.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fan.akua.day3.R;

public class AFragment extends Fragment {
    private static final String TAG = AFragment.class.getName();
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
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
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
