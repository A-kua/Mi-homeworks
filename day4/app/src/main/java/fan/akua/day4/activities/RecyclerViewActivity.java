package fan.akua.day4.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fan.akua.day4.R;
import fan.akua.day4.adapter.MultiAdapter;
import fan.akua.day4.bean.Data;

public class RecyclerViewActivity extends AppCompatActivity {

    private static List<Data> generateRandomData() {
        ArrayList<Data> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(new Data("" + new Random().doubles()));
        }
        return data;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        RecyclerView recyclerView = findViewById(R.id.rv);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.HORIZONTAL));

        MultiAdapter multiAdapter = new MultiAdapter(generateRandomData());
        recyclerView.setAdapter(multiAdapter);

        findViewById(R.id.add).setOnClickListener(v -> multiAdapter.commitData(generateRandomData()));
    }
}
