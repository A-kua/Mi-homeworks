package fan.akua.day9.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import fan.akua.day9.api.GithubService
import fan.akua.day9.bean.Node
import fan.akua.day9.databinding.ActivityHw2Binding
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class HW2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityHw2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHw2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cle.setOnClickListener {
            binding.showTv.text = ""
        }

        binding.useOkHttp.setOnClickListener {
            asyncOkhttpRequest("https://api.github.com/users/octocat/repos") { response ->

                val nodes = Gson().fromJson(response, Array<Node>::class.java)

                val result = nodes.joinToString(separator = "\n") { item ->
                    "id: ${item.id}\nname: ${item.name}\nnode_id: ${item.node_id}\n----\n"
                }
                runOnUiThread {
                    binding.showTv.text = result
                }
            }
        }

        binding.useRetrofit.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(GithubService::class.java)
            service.userRepo.enqueue(object : retrofit2.Callback<List<Node>> {
                override fun onResponse(
                    call: Call<List<Node>>,
                    response: retrofit2.Response<List<Node>>
                ) {
                    val result = response.body()?.joinToString(separator = "\n") { item ->
                        "id: ${item.id}\nname: ${item.name}\nnode_id: ${item.node_id}\n----\n"
                    }
                    runOnUiThread {
                        binding.showTv.text = result
                    }
                }

                override fun onFailure(call: Call<List<Node>>, t: Throwable) {

                }

            })
        }
    }

    // 异步请求
    private fun asyncOkhttpRequest(url: String, callback: (String?) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                callback(response.body()?.string())
            }
        })
    }
}
