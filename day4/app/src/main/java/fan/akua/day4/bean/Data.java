package fan.akua.day4.bean;

import android.graphics.Bitmap;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;
import java.util.Objects;

public class Data {
    private String text;
    public Bitmap bitmap;

    public Data(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Data) {
            Data other = (Data) obj;
            return text.equals(other.text) && bitmap.sameAs(other.bitmap);
        }
        return false;
    }

    public static class DiffCallback extends DiffUtil.Callback {
        private List<Data> oldList;
        private List<Data> newList;

        public DiffCallback(List<Data> oldList, List<Data> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return Objects.equals(oldList.get(oldItemPosition).getText(), newList.get(newItemPosition).getText());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Data oldData = oldList.get(oldItemPosition);
            Data newData = newList.get(newItemPosition);
            return oldData.equals(newData);
        }
    }
}
