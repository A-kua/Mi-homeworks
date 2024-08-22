package fan.akua.day4.bean;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class Data {
    private String text;

    public Data(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static class DiffCallback extends DiffUtil.Callback {
        private final List<Data> old, naw;

        public DiffCallback(List<Data> old, List<Data> naw) {
            this.old = old;
            this.naw = naw;
        }

        @Override
        public int getOldListSize() {
            return old.size();
        }

        @Override
        public int getNewListSize() {
            return naw.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return old.get(oldItemPosition).getText().equals(naw.get(newItemPosition).getText());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return old.get(oldItemPosition).getText().equals(naw.get(newItemPosition).getText());
        }
    }
}
