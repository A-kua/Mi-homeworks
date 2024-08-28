## Day10-Train1

相关的文件如下：

1. [MainActivity.java](app/src/main/java/fan/akua/day10/activities/MainActivity.java)

### 编写代码

两种场景，一直是耗时阻塞，一种是等待锁

![场景1，2](vx_images/63828217575934.png)

### 运行效果如下

点击第一次，并不会出现anr

再次点击，等待5s后出现anr

![anr1](vx_images/506011077664676.png)

![anr2](vx_images/165641800832599.png)