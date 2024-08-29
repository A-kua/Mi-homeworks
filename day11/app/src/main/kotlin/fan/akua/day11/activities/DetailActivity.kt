package fan.akua.day11.activities

import android.os.Bundle
import android.util.Log
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import fan.akua.day11.R
import fan.akua.day11.bean.ImageBean
import fan.akua.day11.bean.TextBean
import fan.akua.day11.databinding.ActivityDetailBinding
import fan.akua.day11.ktx.akuaEdgeToEdge
import fan.akua.day11.repositories.impl.LocalRepository
import fan.akua.day11.utils.RandomBlurTransformation


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        akuaEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val item_id = intent.getIntExtra("item_id", -1)
        val item = LocalRepository.data.first { it.id == item_id }

        binding.star.run {
            setBackgroundResource(if (item.isStarred) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24)
            isChecked = item.isStarred
            setOnCheckedChangeListener { _, isChecked ->
                LocalRepository.starOrUnstar(item_id, true)
                binding.star.setBackgroundResource(if (isChecked) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24)
            }
        }
    }
}