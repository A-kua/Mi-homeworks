package fan.akua.day9.api;

import java.util.Map;

import fan.akua.day9.bean.ApiResponse;
import fan.akua.day9.bean.HotResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HotService {
    @GET("search/hot")
    Call<ApiResponse<HotResponse>> searchHot();

    @FormUrlEncoded
    @POST("post")
    Call<ResponseBody> sendPostRequest(@FieldMap Map<String, String> params);

    @GET("get")
    Call<ResponseBody> sendGetRequest(@Query("key1") String key1, @Query("key2") String key2);

}