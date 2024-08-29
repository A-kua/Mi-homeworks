package fan.akua.day11.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fan.akua.day11.R
import fan.akua.day11.activities.DetailActivity
import fan.akua.day11.api.Repository
import fan.akua.day11.api.impl.LocalRepository
import fan.akua.day11.bean.ImageBean
import fan.akua.day11.bean.StarableBean
import fan.akua.day11.bean.TextBean
import fan.akua.day11.databinding.ItemImgBinding
import fan.akua.day11.databinding.ItemTextBinding
import fan.akua.day11.utils.RandomBlurTransformation
import fan.akua.wrapper.vary.ktx.addFooter
import fan.akua.wrapper.vary.ktx.addModels
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
                val intent = Intent(context, DetailActivity::class.java).apply {
                    val model = getModel<StarableBean>()
                    putExtra("item_id", model.id)
                }
                when (mModel) {
                    is ImageBean -> ItemImgBinding.bind(itemView).let {
                        val model = getModel<ImageBean>()
                        Glide.with(it.img)
                            .load(model.resID)
                            .transform(RandomBlurTransformation(itemView.context))
                            .into(it.img)
                        it.star.isChecked = model.isStarred
                        it.star.setBackgroundResource(if (it.star.isChecked) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24)
                        it.star.setOnCheckedChangeListener { _, isChecked ->
                            LocalRepository.starOrUnstarData(model.id, false)
                            it.star.setBackgroundResource(if (isChecked) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24)
                        }
                        intent.putExtra("item_type", 1)
                    }

                    is TextBean -> ItemTextBinding.bind(itemView).let {
                        val model = getModel<TextBean>()
                        it.tv.text = model.string
                        it.star.isChecked = model.isStarred
                        it.star.setBackgroundResource(if (it.star.isChecked) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24)
                        it.star.setOnCheckedChangeListener { _, isChecked ->
                            LocalRepository.starOrUnstarData(model.id, false)
                            it.star.setBackgroundResource(if (isChecked) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24)
                        }
                        intent.putExtra("item_type", 2)
                    }
                }
                itemView.setOnClickListener {
                    itemView.context.startActivity(intent)
                }
            }
        }
        rv.addModels(LocalRepository.getData())
        lifecycleScope.launch {
            LocalRepository.data.collect { items ->
                rv.recyclerAdapter.models = items
                rv.recyclerAdapter.notifyDataSetChanged()
            }
        }
    }

}