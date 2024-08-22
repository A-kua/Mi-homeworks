package fan.akua.day4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fan.akua.day4.R;
import fan.akua.day4.bean.Data;

public class MultiAdapter extends RecyclerView.Adapter<MultiAdapter.MultiHolder> {

    private List<Data> internalData;

    public MultiAdapter(List<Data> initialData) {
        this.internalData = initialData;
    }

    public void commitData(List<Data> data) {
        ArrayList<Data> temp = new ArrayList<>(data);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new Data.DiffCallback(internalData, temp), false);
        internalData = temp;
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public MultiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MultiHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mutli, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MultiHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(holder.itemView.getContext(), "点击了第" + position, Toast.LENGTH_SHORT).show();
        });
        holder.tv.setText(internalData.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return internalData.size();
    }

    public static class MultiHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MultiHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
