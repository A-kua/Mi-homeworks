package fan.akua.day9.interceptors;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AddHeaderInterceptor implements Interceptor {
    private static final String TAG = AddHeaderInterceptor.class.getName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("token", "UUID{SDSDSD-SSSS-DDDD-SSSS-DDDD-SDSDSD}")
                .build();

        return chain.proceed(request);
    }
}
