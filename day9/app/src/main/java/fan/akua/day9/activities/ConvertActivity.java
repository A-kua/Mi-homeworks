package fan.akua.day9.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import fan.akua.day9.api.HotService;
import fan.akua.day9.bean.ApiResponse;
import fan.akua.day9.bean.Hot;
import fan.akua.day9.bean.HotResponse;
import fan.akua.day9.databinding.ActivityConvertBinding;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConvertActivity extends AppCompatActivity {
    private ActivityConvertBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConvertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.send.setOnClickListener(v -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://193.112.200.228")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            HotService service = retrofit.create(HotService.class);
            Call<ApiResponse<HotResponse>> apiResponseCall = service.searchHot();
            apiResponseCall.enqueue(new Callback<ApiResponse<HotResponse>>() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NonNull Call<ApiResponse<HotResponse>> call, @NonNull Response<ApiResponse<HotResponse>> response) {
                    if (response.isSuccessful()) {
                        ApiResponse<HotResponse> body = response.body();
                        StringBuilder builder = new StringBuilder();
                        assert body != null;
                        builder.append("code ").append(body.getCode());
                        builder.append("list \n");
                        for (Hot hot : body.getResult().getHots()) {
                            builder.append(hot.getFirst()).append("\n");
                            builder.append(hot.getSecond()).append("\n");
                            builder.append(hot.getThird()).append("\n");
                            builder.append(hot.getIconType()).append("\n");
                            builder.append("--------");
                        }
                        binding.tv.setText(builder.toString());
                    } else {
                        binding.tv.setText("请求出错：" + response.errorBody());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ApiResponse<HotResponse>> call, @NonNull Throwable throwable) {
                    binding.tv.setText(throwable.toString());
                }
            });

            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl("https://httpbin.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Map<String, String> params = new HashMap<>();
            params.put("key1", "value1");
            params.put("key2", "value2");

            HotService api = retrofit2.create(HotService.class);
            Call<ResponseBody> call = api.sendPostRequest(params);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Log.d("simon", response.body().toString());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    // 处理错误
                }
            });
        });

    }
}
