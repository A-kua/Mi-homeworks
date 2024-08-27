package fan.akua.day9.api;

import java.util.List;
import java.util.Map;

import fan.akua.day9.bean.ApiResponse;
import fan.akua.day9.bean.HotResponse;
import fan.akua.day9.bean.Node;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GithubService {

    @GET("users/octocat/repos")
    Call<List<Node>> getUserRepo();

}