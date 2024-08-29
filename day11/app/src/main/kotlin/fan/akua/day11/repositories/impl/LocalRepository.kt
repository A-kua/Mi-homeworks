package fan.akua.day11.repositories.impl

import fan.akua.day11.R
import fan.akua.day11.bean.ImageBean
import fan.akua.day11.bean.StarableBean
import fan.akua.day11.bean.TextBean
import fan.akua.day11.repositories.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

object LocalRepository : Repository<StarableBean> {
    private val dataList = mutableListOf<StarableBean>()

    // 很关键
    override var changeFlow: Flow<Int> = MutableSharedFlow(replay = 0)
    override var data: List<StarableBean>
        get() = dataList
        set(value) {}

    init {
        dataList.addAll(
            mutableListOf(
                ImageBean(R.drawable.a63758009_p0, false, 1),
                TextBean(
                    "小镇上有一位老奶奶，她总是把自己的珍贵项链放在一个小盒子里。有一天，项链不见了，老奶奶很伤心。她开始在小镇上询问每个人，最终在一个小女孩的手中找到了项链。小女孩告诉她，她在公园里捡到的。老奶奶感动之余，决定送给小女孩一颗漂亮的珠子，作为友情的象征。",
                    false, 13
                ),
                ImageBean(R.drawable.a64041757_p0, false, 2),
                TextBean(
                    "有一天，一个年轻人发现了一块神秘的怀表。每当他转动表针，时间就会倒退一小时。起初，他用它来避免一些小麻烦，但很快他意识到，时间是不能被随意改变的。最终，他决定把怀表放回原处，珍惜当下的每一刻，因为每一秒都是独一无二的礼物。",
                    false, 15
                ),
                ImageBean(R.drawable.a64445944_p0, false, 3),
                ImageBean(R.drawable.a64675100_p0, false, 4),
                TextBean(
                    "在一个神秘的森林里，动物们每天都会聚在一起唱歌。一天，森林里来了一只迷路的小鸟，它的歌声动听却没有伙伴。动物们邀请小鸟加入他们的合唱，大家一起创造出美妙的旋律。从此，小鸟不再孤单，森林里回荡着快乐的歌声。",
                    false, 16
                ),
                ImageBean(R.drawable.a65128995_p0, false, 5),
                ImageBean(R.drawable.a67046987_p0, false, 8),
                TextBean(
                    "有一个小镇，所有的东西都是黑白的。一个小男孩决定去寻找颜色。他走过山谷、河流，经历了许多冒险，最终在一个神奇的地方发现了五彩斑斓的世界。小男孩把颜色带回了小镇，从此小镇变得生机勃勃，居民们学会了欣赏生活中的美好。",
                    false, 18
                ),
                ImageBean(R.drawable.a67334024_p0, false, 9),
                ImageBean(R.drawable.a68153463_p0, false, 10),
                TextBean(
                    "在一个宁静的池塘边，住着一只青蛙。它总是梦想能跳出池塘，看看外面的世界。一天，它鼓起勇气，跳出了池塘，发现了金色的沙滩和高耸的山峰。青蛙游历了许多地方，结识了新朋友，最后它带着满满的经历和故事回到了池塘，成为了其他青蛙心中的英雄。",
                    false, 14
                ),
                TextBean(
                    "小女孩在花园里种下了一颗种子，她每天都浇水、施肥，期待着它能长大。有一天，种子终于发芽了，长成了一朵美丽的花。小女孩不仅收获了花朵，还学会了耐心和坚持。她把花送给了妈妈，表达了自己的爱，妈妈感动得热泪盈眶。",
                    false, 17
                ),

                )
        )

    }

    override fun starOrUnstar(id: Int, notify: Boolean) {
        dataList.mapIndexed { index, bean ->
            if (bean.id == id) {
                bean.isStarred = !bean.isStarred
            }
            if (notify)
                emitData(index)
            bean
        }
    }

    private fun emitData(position: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            (changeFlow as MutableSharedFlow<Int>).emit(position)
        }
    }

}