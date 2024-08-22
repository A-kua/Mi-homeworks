package fan.akua.day4.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fan.akua.day4.R;

public class HW1Fragment extends Fragment {
    public static HW1Fragment newInstance() {
        return new HW1Fragment();
    }

    private HW1Fragment() {
        super(R.layout.fragment_hw1);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView imageView = view.findViewById(R.id.img);
        SeekBar seekBar = view.findViewById(R.id.seek);
        EditText editText = view.findViewById(R.id.edit);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        ViewGroup viewGroup = view.findViewById(R.id.checkGroup);
        Button button = view.findViewById(R.id.btn);

        seekBar.setMax(360);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageView.setPivotX(imageView.getWidth() / 2);
                imageView.setPivotY(imageView.getHeight() / 2);
                imageView.setRotation(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(v -> {
            StringBuilder builder = new StringBuilder();
            builder.append("EditText内容为：")
                    .append(editText.getText().toString());
            RadioButton radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
            builder.append("\n");
            builder.append("RadioButton内容为：")
                    .append(radioButton.getText().toString());
            builder.append("\n");
            int childCount = viewGroup.getChildCount();
            builder.append("CheckBox选中了：");
            for (int i = 0; i < childCount; i++) {
                CheckBox childAt = (CheckBox) viewGroup.getChildAt(i);
                if (childAt.isChecked())
                    builder.append(childAt.getText().toString());
            }
            builder.append("\n");
            String string = builder.toString();
            Toast.makeText(getContext(), string, Toast.LENGTH_LONG).show();
        });
    }
}
