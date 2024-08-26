package fan.akua.day8.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fan.akua.day8.R;

public class FlowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        Toast.makeText(this, "ss", Toast.LENGTH_SHORT).show();
    }
}
