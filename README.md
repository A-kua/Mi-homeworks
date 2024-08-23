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


[任务目录](day5)