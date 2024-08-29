<div>
    <p align="center">
        <strong>nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao</strong>
        <br>
    </p>
</div>

## Day1

>1. 搭建完成AndroidStudio环境，项目能够正常运行展示Hello World。
>2. 代码上传到自己的Gitlab仓库。
>3. 配置adb命令到环境变量，同时用adb命令安装到手机。
>4. 使用gradle命令编译apk。

[任务目录](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/tree/main/day1)

## Day2

>1. 新建Activity，通过日志打印，熟悉生命周期执行顺序。
>2. 为Activity设置不同的启动模式并通过adb命令进行查看栈信息，同时打印生命周期日志。（注意：要打印onNewIntent周期）
>3. 使用隐式Intent启动拨号、手机联系人、短信。
>4. 通过绑定服务的方式实现组件间通信，实现Activity与Service通信。
>5. 查询了解广播接收器如何异步处理任务，使用goAsync()方式实现异步任务处理。
>6. 使用ContentProvider读取手机中的相册图片。

>1. 打印四种启动模式启动Activity的生命周期并总结。
>2. 打印startService和bindService生命周期并总结。
>3. 分别使用动态注册和静态注册广播，并接受广播日志。
>4. 使用AIDL进行跨进程通信。

[任务目录](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/tree/main/day2)

## Day3

>1. 掌握创建并显示Fragment的两种方式。（为Fragment传递初始参数）
>2. 使用add、show、hide、replace操作Fragment。
>3. 打印Fragment的生命周期。分析add、hide、replace以及按下Home键的日志差异。
>4. 掌握Fragment通信方式：点击FragmentA中的按钮，将FragmentB添加到Activity的另一个区域；FragmentB暴露一个public方法，让Activity可以修改他的背景色；使用FragmentResult API实现FragmentA接受FragmentB的返回；
>5. 使用ViewPager实现水平滑动布局，在滑动页面时分析Fragment生命周期变化。
>6. 学习使用Fragment动画。为FragmentB添加进入和退出动画，并让他替换FragmentA。使用共享元素展示FragmentB。

>1. 使用Fragment组成一个复杂页面：搭建App首页，一个Activity有多个Fragment；点击底部TAB切换Fragment；Fragment只显示一段文本即可；点击按钮跳转到另一个Fragment；
>2. 使用ViewPager实现Fragment左右滑动；

[任务目录](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/tree/main/day3)

## Day4

>1. 练习使用TextView：完成富文本的下划线和删除线。
>2. 编写课上展示的布局页面。
>3. 编写用户登录页面。
>4. 编写一个具有ImageView、TextView、Button的页面。
>5. 使用RecyclerView展示列表。
>6. 点击按钮添加Item、删除Item。
>7. 使用ViewPager实现滑动页面。

>1. 在一个fragment中实现拥有TextView、EditText、RadioButton、CheckBox、Button、SeekBar、ImageView。点击Button能Toast打印EditText的内容，RadioButton、CheckBox的选项。其中EditText只能输入大写字母，同时输入的大写字母要以*显示。
>2. 实现一个列表（20项数据，10种不同的图片、文字，其中1、11图片相同，2、12图片相同以此类推），要求具有点击事件，Item样式需包括文字图片，并且包含列表的position信息，实现增加、删除列表项。
>3. 在2的基础上，增加或者删除后，所有项显示的position值需要同步更新，支持用户页面指定放置位置（如果用户指定的值超过最大值，最后一个项的位置+1，告诉用户位置过大，小于第一个位置放在第一个位置）。

[任务目录](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/tree/main/day4)

## Day5

>1. 实现一个布局，使其两个文本框层叠并显示在页面底部。（扩展：代码实现布局效果）
>2. 使用LinearLayout和FrameLayout实现 [附图1] 的页面。（扩展：代码实现布局效果）
>3. 使用RelativeLayout实现设置页面的布局。
>4. 用ConstraintLayout实现应用的关于页面。

>1. 实现[附图3]，使用最少布局层级，查看页面过度绘制情况。使用include优化代码，减少xml代码数量。

[任务目录](day5)

## Day6

>1. 使用Glide加载图片。
>2. 练习EventBus。在ActivityB中发布事件，ActivityA中接收事件。
>3. 使用BRVAH构造瀑布流布局，展示两列图片。
>4. 使用SwipeRefreshLayout实现下拉加载。
>5. 使用BRVAH的上拉加载功能。

>1. 运用组件实现滑动列表，支持点击查看详情大图和文案，包含下拉刷新功能。（两个页面，一个**首页**，一**个我的**，可以通过点击底部按钮进行切换。首页有一个列表，列表中有两种类型：文字和图片。点击列表条目可以跳转详细页。如果点击文字则详细页展示文字，如果点击图片则详细页展示图片。首页需要支持下拉刷新。首页图文和详细页面支持点赞功能。）

[任务目录](day6)

## Day7

>1. 使用帧动画展示一段效果。
>2. 使用补间动画展示一段效果。
>3. 使用scale展示一段效果。
>4. 使用组合动画展示一段效果。
>5. 使用属性动画，让view沿x轴翻转360°。
>6. 使用ValueAnimator实现翻转同时平移。
>7. 使用PropertyAnimator实现 [6] 的动画。

>1. 使用补间动画，基于当前View中心点放大1.5倍，同时进行逆时针旋转720°，由不透明转为透明0.8，持续2s，并重复动画3次。（任选XML或者Java，动画开始时打印日志“animation start”，动画重复时打印”animation repeat“已经重复了x次，动画结束时打印”animation end“）
>2. 使用属性动画。使用AnimatorSet，先让View绕x轴旋转360°，持续1s。然后向右移动120px，持续1.2s。最后从不透明变成透明的0.5，持续0.5s。（使用Java实现，需要2个基础动画同时执行，有一个顺序执行，且至少实现2种不同效果的自定义插值器与估值器）

[任务目录](day7)

## Day8

>1. 自定义EditText，添加边框。
>2. 自定义ViewGroup，实现FlowLayout效果。
>3. 自定义ViewGroup，实现拖拽交互效果。
>4. 自定义View，实现拖动、缩放、旋转的效果。

>1. 完成“View云”效果。 
>2. 完成View跟随手势滑动。

[任务目录](day8)

## Day9

>1. 申请相机权限拍照。
>2. 读联系人，打电话。
>3. okhttp网络请求。
>4. okhttp请求加入拦截器。

>1. 相机权限请求。
>2. Retrofit网络请求。

[任务目录](day9)

## Day10

>1. 测试ANR。

>1. 优化ANR-WatchDog检测ANR机制。（将原来的5s发送一次改为1s发送一次，假如累计有5次发出且5次都回不了包，则表示有ANR的现象，再采集线程信息）
>2. 复现7种内存泄漏场景。

[任务目录](day10)

## Day11

>1. 使用Kotlin打印一个自我介绍。。

>1. Kotlin实现滑动列表，支持点击查看详细大图和文案（两个页面，一个首页，一个我的；可以通过点击底部按钮进行切换。首页使用RecyclerView展示一个列表，列表中有两种类型，分别是文字和图片，两种类型均支持点赞；点击列表条目可以跳转详细页。如果点击文字则详细页展示文字，如果点击图片则详细页展示图片；详细页可以点赞和取消点赞，操作后状态同步到首页；）

[任务目录](day11)
