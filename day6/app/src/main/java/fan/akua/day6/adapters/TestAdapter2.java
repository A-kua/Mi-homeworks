package fan.akua.day6.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import fan.akua.day6.R;
import fan.akua.day6.bean.ImgBean;

public class TestAdapter2 extends BaseQuickAdapter<String, TestAdapter2.TestAdapter2Holder> {

    public TestAdapter2(@Nullable List<String> data) {
        super(R.layout.item_test2, data);
    }

    @Override
    protected void convert(@NonNull TestAdapter2.TestAdapter2Holder testAdapterHolder, String text) {
        testAdapterHolder.textView.setText(text);
    }

    public static class TestAdapter2Holder extends BaseViewHolder {
        TextView textView;

        public TestAdapter2Holder(@NonNull View view) {
            super(view);
            textView = view.findViewById(R.id.tv);
        }
    }
}
