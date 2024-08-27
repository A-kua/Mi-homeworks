package fan.akua.day9.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fan.akua.day9.R
import fan.akua.day9.databinding.ActivityMainBinding
import fan.akua.day9.databinding.ActivityNetBinding
import fan.akua.day9.interceptors.AddHeaderInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class NetActivity : AppCompatActivity() {
    lateinit var binding: ActivityNetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 同步请求
        binding.sync.setOnClickListener {
            GlobalScope.launch {
                val result = syncRequest("https://httpbin.org/get")
                runOnUiThread {
                    binding.resu.text = result ?: "Error"
                }
            }
        }
        // 异步请求
        binding.async.setOnClickListener {
            asyncRequest("https://httpbin.org/get") { result ->
                runOnUiThread {
                    binding.resu.text = result ?: "Error"
                }
            }
        }
        // post请求拦截器
        binding.po.setOnClickListener {
            val client = OkHttpClient.Builder()
                .addInterceptor(AddHeaderInterceptor())
                .build()
            val formBody = FormBody.Builder()
                .add("username", "测试账号")
                .add("password", "123456")
                .build();

            val request = Request.Builder()
                .post(formBody)
                .url("https://httpbin.org/post")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    runOnUiThread {
                        binding.resu.text = "Error"
                    }
                }

                override fun onResponse(call: okhttp3.Call, response: Response) {
                    runOnUiThread {
                        binding.resu.text = response.body()?.string()
                    }
                }
            })
        }
    }

    // 同步请求(使用协程)
    private suspend fun syncRequest(url: String): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        return withContext(Dispatchers.IO) {
            val response = client.newCall(request).execute()
            response.body()?.string() ?: ""
        }
    }

    // 异步请求
    private fun asyncRequest(url: String, callback: (String?) -> Unit) {
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
