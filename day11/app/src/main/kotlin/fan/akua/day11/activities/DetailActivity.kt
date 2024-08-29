package fan.akua.day11.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import fan.akua.day11.R
import fan.akua.day11.api.impl.LocalRepository
import fan.akua.day11.bean.ImageBean
import fan.akua.day11.bean.TextBean
import fan.akua.day11.databinding.ActivityDetailBinding
import fan.akua.day11.ktx.akuaEdgeToEdge
import fan.akua.day11.utils.RandomBlurTransformation


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        akuaEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val itemId = intent.getIntExtra("item_id", 0)

        val data = LocalRepository.getData().first {
            it.id == itemId
        }

        val type = intent.getIntExtra("item_type", 0)
        when (type) {
            1 -> {
                data as ImageBean
                Glide.with(this)
                    .load(data.resID)
                    .transform(RandomBlurTransformation(this))
                    .into(binding.img)
            }

            2 -> {
                data as TextBean
                binding.tv.text = data.string
            }
        }
        binding.star.isChecked = data.isStarred
        binding.star.setBackgroundResource(if (binding.star.isChecked) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24)
        binding.star.setOnCheckedChangeListener { _, isChecked ->
            LocalRepository.starOrUnstarData(itemId, true)
            binding.star.setBackgroundResource(if (isChecked) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24)
        }

    }
}