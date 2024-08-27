package fan.akua.day9.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fan.akua.day9.R
import fan.akua.day9.databinding.ActivityMainBinding
import fan.akua.day9.databinding.ActivityNetBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
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
                val result = syncRequest("https://cs.ahu.edu.cn")
                runOnUiThread {
                    binding.resu.text = result ?: "Error"
                }
            }
        }
        // 异步请求
        binding.async.setOnClickListener {
            asyncRequest("https://cs.ahu.edu.cn") { result ->
                runOnUiThread {
                    binding.resu.text = result ?: "Error"
                }
            }
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
