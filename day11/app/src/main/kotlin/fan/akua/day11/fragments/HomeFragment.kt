package fan.akua.day11.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fan.akua.day11.R
import fan.akua.day11.activities.DetailActivity
import fan.akua.day11.bean.ImageBean
import fan.akua.day11.bean.StarableBean
import fan.akua.day11.bean.TextBean
import fan.akua.day11.databinding.ItemImgBinding
import fan.akua.day11.databinding.ItemTextBinding
import fan.akua.day11.repositories.impl.LocalRepository
import fan.akua.day11.utils.RandomBlurTransformation
import fan.akua.wrapper.vary.ktx.addModels
import fan.akua.wrapper.vary.ktx.linear
import fan.akua.wrapper.vary.ktx.recyclerAdapter
import fan.akua.wrapper.vary.ktx.setup
import fan.akua.wrapper.vary.ktx.staggered
import kotlinx.coroutines.launch


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var rv: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv = view.findViewById(R.id.rv)

        rv.staggered(2).setup {
            addType<ImageBean>(R.layout.item_img)
            addType<TextBean>(R.layout.item_text)
            onBind {
                val model = getModel<StarableBean>()
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("item_id", model.id)
                }
                when (model) {
                    is ImageBean -> ItemImgBinding.bind(itemView).let {
                        Glide.with(it.img)
                            .load(model.resID)
                            .transform(RandomBlurTransformation(itemView.context))
                            .into(it.img)
                        intent.putExtra("item_type", 1)
                    }

                    is TextBean -> ItemTextBinding.bind(itemView).let {
                        it.tv.text = model.string
                        intent.putExtra("item_type", 2)
                    }
                }
                itemView.setOnClickListener {
                    itemView.context.startActivity(intent)
                }
                val starView = itemView.findViewById<ToggleButton>(R.id.star)
                starView.setBackgroundResource(if (model.isStarred) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24)
                starView.setOnCheckedChangeListener { _, isChecked ->
                    LocalRepository.starOrUnstar(model.id, false)
                    starView.setBackgroundResource(if (isChecked) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24)
                }
            }
        }

        rv.addModels(LocalRepository.data)
        lifecycleScope.launch {
            LocalRepository.changeFlow.collect { position ->
                rv.recyclerAdapter.notifyItemChanged(position)
            }
        }


    }

}