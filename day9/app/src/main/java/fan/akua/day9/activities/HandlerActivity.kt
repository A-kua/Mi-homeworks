package fan.akua.day9.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import fan.akua.day9.databinding.ActivityHandlerBinding

class HandlerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHandlerBinding
    private val handler = Handler(Looper.getMainLooper())
    private var time = 10000
    private val runnable = object : Runnable {
        override fun run() {
            if (time == 0) {
                binding.tv.text = "倒计时结束"
                return
            }
            binding.tv.text = "剩余时间：$time"
            time -= 1000
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            handler.postDelayed(runnable, 1000)
        }
    }

    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        super.onDestroy()
    }
}
