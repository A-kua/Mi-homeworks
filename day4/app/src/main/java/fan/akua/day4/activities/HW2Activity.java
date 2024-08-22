package fan.akua.day4.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fan.akua.day4.R;
import fan.akua.day4.adapter.HW2Adapter;
import fan.akua.day4.bean.Data;

public class HW2Activity extends AppCompatActivity {
    private static final int[] resIDs = new int[]{
            R.drawable.a1,
            R.drawable.a2,
            R.drawable.a3,
            R.drawable.a4,
            R.drawable.a5,
            R.drawable.a6,
            R.drawable.a7,
            R.drawable.a8,
            R.drawable.a9,
            R.drawable.a10,
    };

    private EditText editText;
    private RecyclerView rv;
    private HW2Adapter adapter;

    private List<Data> getData() {
        return IntStream.range(0, 20)
                .parallel().mapToObj(value -> {
                    Data data = new Data("第" + value + "个数据");
                    data.bitmap = BitmapFactory.decodeResource(getResources(), resIDs[value % 10]);
                    return data;
                }).collect(Collectors.toList());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw2);

        editText = findViewById(R.id.edit);
        rv = findViewById(R.id.rv);

        rv.setAdapter(adapter = new HW2Adapter(getData()));

        findViewById(R.id.add).setOnClickListener(v -> {
            int l = (int) System.currentTimeMillis();
            Data data = new Data("新加的 " + l);
            data.bitmap = BitmapFactory.decodeResource(getResources(), resIDs[l % 10]);
            try {
                int i = Integer.parseInt(editText.getText().toString());
                adapter.addItem(i, data);
            } catch (NumberFormatException e) {
                Toast.makeText(HW2Activity.this, "请输入数字", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(HW2Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.del).setOnClickListener(v -> {
            try {
                int i = Integer.parseInt(editText.getText().toString());
                adapter.removeItem(i);
            } catch (NumberFormatException e) {
                Toast.makeText(HW2Activity.this, "请输入数字", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(HW2Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
