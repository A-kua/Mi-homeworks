package fan.akua.day4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fan.akua.day4.R;
import fan.akua.day4.bean.Data;

public class HW2Adapter extends RecyclerView.Adapter<HW2Adapter.HW2Holder> {
    private List<Data> internalData;

    public HW2Adapter(List<Data> initialData) {
        this.internalData = initialData;
    }

    public void addItem(int index, Data data) throws Exception {
        if (index < 0) {
            throw new Exception("值过小");
        } else if (index >= internalData.size()) {
            throw new Exception("值过大");
        }
        List<Data> newList = new ArrayList<>(internalData);
        newList.add(index, data);

        commitData(newList);
    }

    public void removeItem(int index) throws Exception {
        if (index < 0) {
            throw new Exception("值过小");
        } else if (index >= internalData.size()) {
            throw new Exception("值过大");
        }
        List<Data> newList = new ArrayList<>(internalData);
        newList.remove(index);
        commitData(newList);
    }

    private void commitData(List<Data> data) {
        Data.DiffCallback diffCallback = new Data.DiffCallback(internalData, data);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        internalData = data;
        diffResult.dispatchUpdatesTo(this);
        notifyItemRangeChanged(0, data.size());
    }

    @NonNull
    @Override
    public HW2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HW2Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hw2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HW2Holder holder, int position) {
        holder.btn.setText("第" + position + "个元素");
        holder.img.setImageBitmap(internalData.get(position).bitmap);
        holder.btn.setOnClickListener(v -> Toast.makeText(holder.itemView.getContext(), "你点击了" + position, Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return internalData.size();
    }

    public static class HW2Holder extends RecyclerView.ViewHolder {
        Button btn;
        ImageView img;

        public HW2Holder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
            img = itemView.findViewById(R.id.img);
        }
    }
}
