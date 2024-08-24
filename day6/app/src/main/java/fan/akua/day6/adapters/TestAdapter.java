package fan.akua.day6.adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import fan.akua.day6.R;
import fan.akua.day6.bean.ImgBean;

public class TestAdapter extends BaseQuickAdapter<ImgBean, TestAdapter.TestAdapterHolder> {

    public TestAdapter(@Nullable List<ImgBean> data) {
        super(R.layout.item_test, data);
    }

    @Override
    protected void convert(@NonNull TestAdapterHolder testAdapterHolder, ImgBean imgBean) {
        Glide.with(testAdapterHolder.imageView)
                .load(imgBean.getUrl())
                .into(testAdapterHolder.imageView);
    }

    public static class TestAdapterHolder extends BaseViewHolder {
        ImageView imageView;

        public TestAdapterHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.img);
        }
    }
}
