## Day3-Train1

相关的文件如下：
1. [AFragment.java](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/blob/main/day3/app/src/main/java/fan/akua/day3/fragments/AFragment.java)
2. [BFragment.java](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/blob/main/day3/app/src/main/java/fan/akua/day3/fragments/BFragment.java)
3. [MainActivity.java](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/blob/main/day3/app/src/main/java/fan/akua/day3/activities/MainActivity.java)

### 先编写布局

对于activity_main的布局，如下

![activity_main](vx_images/70857482066520.png)

对于AFragment的布局如下，他是动态添加的

![fragment_a](vx_images/308394262664864.png)

对于BFragment的布局如下，他是静态添加的

![fragment_b](vx_images/575585258204217.png)

### 编写Activity代码

![MainActivity](vx_images/326647425366013.png)

很简单，当也需要注意：**Fragment在Activity重启时会自动重新创建，为了避免重复创建需要判断savedInstanceState。**

### 编写Fragment

![AFragment](vx_images/465574878298974.png)

值得注意的是：**Fragment在自动创建时，使用的是反射，且只使用无参构造函数。一个Fragment必须声明无参构造，同时对于动态注册的Fragment，如果需要传输数据，最好进行一次封装。**

### 运行效果如下

![运行](vx_images/Screenshot_20240821_094456.png)
