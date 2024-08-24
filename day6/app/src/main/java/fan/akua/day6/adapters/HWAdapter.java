package fan.akua.day6.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import fan.akua.day6.R;
import fan.akua.day6.activities.DetailActivity;
import fan.akua.day6.bean.MultiBean;

public class HWAdapter extends BaseMultiItemQuickAdapter<MultiBean, HWAdapter.HWHolder> {
    public HWAdapter(List<MultiBean> data) {
        super(data);
        addItemType(MultiBean.Type.Img.ordinal(), R.layout.item_img);
        addItemType(MultiBean.Type.Txt.ordinal(), R.layout.item_txt);
    }

    @Override
    protected void convert(@NonNull HWHolder hwHolder, MultiBean multiBean) {
        final int img_type = MultiBean.Type.Img.ordinal();
        final int txt_type = MultiBean.Type.Txt.ordinal();
        if (hwHolder.getItemViewType() == img_type) {
            Glide.with(hwHolder.itemView).load(multiBean.getImgUrl()).into(hwHolder.img);
        } else if (hwHolder.getItemViewType() == txt_type) {
            hwHolder.txt.setText(multiBean.getTxt());
        }

        hwHolder.star.setBackgroundResource(multiBean.isStated() ? R.drawable.baseline_star_24 : R.drawable.baseline_star_border_24);

        hwHolder.star.setOnCheckedChangeListener((buttonView, isChecked) -> {
            multiBean.setStated(isChecked);
            hwHolder.star.setBackgroundResource(isChecked ? R.drawable.baseline_star_24 : R.drawable.baseline_star_border_24);
        });
        hwHolder.itemView.setOnClickListener(v -> {
            Context context = hwHolder.itemView.getContext();
            if (hwHolder.getItemViewType() == MultiBean.Type.Txt.ordinal()) {
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, hwHolder.txt, "shareTxt").toBundle();
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("dat", multiBean);
                intent.putExtra("pos", getItemPosition(multiBean));
                context.startActivity(intent, bundle);
            } else if (hwHolder.getItemViewType() == MultiBean.Type.Img.ordinal()) {
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, hwHolder.img, "shareImg").toBundle();
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("dat", multiBean);
                intent.putExtra("pos", getItemPosition(multiBean));
                context.startActivity(intent, bundle);
            } else {
                throw new RuntimeException("what's wrong with you?!");
            }
        });

    }

    public static class HWHolder extends BaseViewHolder {
        ImageView img;
        TextView txt;
        ToggleButton star;

        public HWHolder(@NonNull View view) {
            super(view);
            txt = view.findViewById(R.id.tv);
            img = view.findViewById(R.id.img);
            star = view.findViewById(R.id.star);
        }
    }
}
