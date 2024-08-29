## Day11-HW1

相关的文件较多，请直接阅读源码。

### 编写布局

整体采用Navigation+BottomNavigationView

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/nav_view"
        style="@style/Widget.Material3.BottomNavigationView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom"
        android:alpha="0.95"
        app:itemRippleColor="@null"
        app:labelVisibilityMode="labeled"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:menu="@menu/menu_nav" />

    <fragment
        android:id="@+id/nav_host_fragment_activity_main"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:defaultNavHost="true"

        app:layout_constraintBottom_toTopOf="@id/nav_view"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:navGraph="@navigation/navigation_hw" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### 编写Fragment

先写menu吧

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/navigation_home"
        android:icon="@drawable/baseline_home_24"
        android:title="@string/tab_home" />

    <item
        android:id="@+id/navigation_mine"
        android:icon="@drawable/baseline_person_24"
        android:title="@string/tab_mine" />

</menu>
```

再编写导航

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/mobile_navigation"
    app:startDestination="@+id/navigation_home">

    <fragment
        android:id="@+id/navigation_home"
        android:name="fan.akua.day11.fragments.HomeFragment"
        android:label="@string/tab_home"
        tools:layout="@layout/fragment_home" />

    <fragment
        android:id="@+id/navigation_mine"
        android:name="fan.akua.day11.fragments.MineFragment"
        android:label="@string/tab_mine"
        tools:layout="@layout/fragment_mine" />

</navigation>
```

#### HomeFragment

用了我自己写的Vary框架，快速构建RV适配器

[Vary](https://github.com/AquaApps/AkuaX/tree/main/wrapper/vary)

```kotlin
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
```

#### MineFragment

这个简单，我就放个WebView罢

```kotlin
class MineFragment : Fragment(R.layout.fragment_mine) {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webview)
        webView.setWebViewClient(WebViewClient())
        webView.getSettings().javaScriptEnabled = true
        webView.loadUrl("http://blog.akua.fan")
    }

    override fun onDestroyView() {
        webView.destroy()
        super.onDestroyView()
    }
}
```

### 编写Activity

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        akuaEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)
    }
}
```

### 定义数据来源

写个接口。不用liveData是因为它有数据倒灌问题

```kotlin
interface Repository<D> {
    var changeFlow: Flow<Int>
    var data: List<D>
    fun starOrUnstar(id: Int, notify: Boolean)
}
```

接口实现。直接单例了。

这里采用唯一数据源+修改流。

```kotlin
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
                ...
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
```

### 详细页

写的不是很优雅

```kotlin
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
```

### 运行效果如下

[视频无法播放请点击我](vx_images/Screen_recording_20240829_145500.mp4)

<div>
    <video src="vx_images/Screen_recording_20240829_145500.mp4"></video>
</div>
