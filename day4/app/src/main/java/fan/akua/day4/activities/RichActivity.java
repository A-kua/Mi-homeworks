package fan.akua.day4.activities;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StrikethroughSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day4.R;

public class RichActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich);
        tv = findViewById(R.id.richTv);
        findViewById(R.id.btn).setOnClickListener(v -> {
            String text = "这是一段富文本哦，试试下划线，试试删除线。";
            SpannableString ss = new SpannableString(text);
            ss.setSpan(new UnderlineSpan(),text.indexOf("下划线"),text.indexOf("下划线")+3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            ss.setSpan(new StrikethroughSpan(),text.indexOf("删除线"),text.indexOf("删除线")+3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            tv.setText(ss);
//            tv.setText(Html.fromHtml());
//             SPAN_EXCLUSIVE_EXCLUSIVE 前后都不包含
//             Spanned.SPAN_EXCLUSIVE_INCLUSIVE 不包含前面 包含后面
//             Spanned.SPAN_INCLUSIVE_EXCLUSIVE 包含前面，不包含后面
//             Spanned.SPAN_INCLUSIVE_INCLUSIVE 前后都包含
        });

    }
}
