package fan.akua.day5.activities;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.textclassifier.TextLinks;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import fan.akua.day5.R;

public class ConstraintActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        TextView tv1 = findViewById(R.id.tv1);
        String string = tv1.getText().toString();
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new URLSpan("https://blog.akua.fan"), 2, string.length(),
                Spanned.SPAN_INTERMEDIATE);
        tv1.setText(spannableString);

        TextView declare = findViewById(R.id.declare);
        String string2 = declare.getText().toString();
        SpannableString spannableString2 = new SpannableString(string2);
        spannableString2.setSpan(new URLSpan("https://blog.akua.fan"), 0, string2.length(),
                Spanned.SPAN_INTERMEDIATE);
        declare.setText(spannableString2);

        tv1.setMovementMethod(LinkMovementMethod.getInstance());
        declare.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
