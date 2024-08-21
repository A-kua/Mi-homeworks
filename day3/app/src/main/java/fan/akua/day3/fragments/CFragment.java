package fan.akua.day3.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentResultListener;

import fan.akua.day3.R;
import fan.akua.day3.base.BaseFragment;

public class CFragment extends BaseFragment implements FragmentResultListener {

    @Override
    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
        if (requestKey.equals(DFragment.REQUEST_KEY)) {
            Toast.makeText(getContext(), "收到消息了" + result.getString(DFragment.REQUEST_KEY), Toast.LENGTH_SHORT).show();
        }
    }

    public static interface BridgeInterface {
        void onCommunicate();
    }

    public static CFragment newInstance(BridgeInterface bridgeInterface) {
        CFragment fragment = new CFragment();
        return fragment.setBridge(bridgeInterface);
    }

    private BridgeInterface bridgeInterface;

    private CFragment() {
        super(R.layout.fragment_c);
    }

    public CFragment setBridge(BridgeInterface bridgeInterface) {
        this.bridgeInterface = bridgeInterface;
        return this;
    }

    @Override
    protected String getLogTag() {
        return "CFragment";
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.add).setOnClickListener(v -> {
            if (bridgeInterface != null)
                bridgeInterface.onCommunicate();
        });
        view.findViewById(R.id.req).setOnClickListener(v -> DFragment.request(getParentFragmentManager(), CFragment.this, CFragment.this));
    }
}
