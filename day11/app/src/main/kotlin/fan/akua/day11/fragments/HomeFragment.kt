package fan.akua.day11.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fan.akua.day11.R
import fan.akua.day11.bean.FooterBean
import fan.akua.day11.bean.ImageBean
import fan.akua.day11.bean.TextBean
import fan.akua.day11.databinding.ItemImgBinding
import fan.akua.day11.databinding.ItemTextBinding
import fan.akua.day11.utils.RandomBlurTransformation
import fan.akua.wrapper.vary.ktx.addFooter
import fan.akua.wrapper.vary.ktx.addModels
import fan.akua.wrapper.vary.ktx.setup
import fan.akua.wrapper.vary.ktx.staggered

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var rv: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv = view.findViewById(R.id.rv)

        rv.staggered(2).setup {
            addType<ImageBean>(R.layout.item_img)
            addType<TextBean>(R.layout.item_text)
            onBind {
                when (mModel) {
                    is ImageBean -> ItemImgBinding.bind(itemView).let {
                        val model = getModel<ImageBean>()
                        Glide.with(it.img)
                            .load(model.resID)
                            .transform(RandomBlurTransformation(itemView.context))
                            .into(it.img);
                    }

                    is TextBean -> ItemTextBinding.bind(itemView).let {
                        val model = getModel<TextBean>()
                        it.tv.text = model.string
                    }

                }
            }
        }

        rv.addModels(
            listOf(
                ImageBean(R.drawable.a63758009_p0),
                ImageBean(R.drawable.a64041757_p0),
                ImageBean(R.drawable.a64445944_p0),
                ImageBean(R.drawable.a64675100_p0),
                ImageBean(R.drawable.a65128995_p0),
                ImageBean(R.drawable.a65459262_p0),
                ImageBean(R.drawable.a66637046_p0),
                ImageBean(R.drawable.a67046987_p0),
                ImageBean(R.drawable.a67334024_p0),
                ImageBean(R.drawable.a68153463_p0),
                ImageBean(R.drawable.a70105195_p0),
                ImageBean(R.drawable.a71175168_p0),
                TextBean("文本111234567890-0987654323456789098765456789"),
                TextBean("文本111234567890-0987654323456789098765456789"),
                TextBean("文本111234567890-0987654323456789098765456789"),
                TextBean("文本111234567890-0987654323456789098765456789"),
                TextBean("文本111234567890-0987654323456789098765456789"),
                TextBean("文本111234567890-0987654323456789098765456789"),
            ).shuffled()
        )

    }

}