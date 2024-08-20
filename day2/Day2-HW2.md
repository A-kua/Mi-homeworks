## Day2-HW2

相关的文件如下：
1. [TestService.java](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/blob/main/day2/app/src/main/java/fan/akua/day2/service/TestService.java)
2. [ServiceControllerActivity.java](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/blob/main/day2/app/src/main/aidl/fan/akua/day2/activities/ServiceControllerActivity.java)

### 编写Activity代码

在Activity中定义startService、stopService、bindService、unbindService操作

![Activity代码](pic/76422441705956.png)

### 运行效果如下

[视频无法播放请点击我](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/tree/main/day2/pic/20240820_185949.mp4)

<div>
    <video src="pic/20240820_185949.mp4"></video>
</div>

### 小结

如果不考虑startService和bindService混用，则startService方式的生命周期如下

```text
+ - - - - - - - - - - - -+
'  Android Service 生命周期  '
'                        '
' +--------------------+ '
' |      onCreate      | '
' +--------------------+ '
'   |                    '
'   | startService       '
'   v                    '
' +--------------------+ '
' |   onStartCommand   | '
' +--------------------+ '
'   |                    '
'   | startService       '
'   v                    '
' +--------------------+ '
' |   onStartCommand   | '
' +--------------------+ '
'   |                    '
'   | 销毁                '
'   v                    '
' +--------------------+ '
' |     onDestroy      | '
' +--------------------+ '
'                        '
+ - - - - - - - - - - - -+
```

而bindService生命周期如下

```text
+ - - - - - - - - - - - -+
'  Android Service 生命周期  '
'                        '
' +--------------------+ '
' |      onCreate      | '
' +--------------------+ '
'   |                    '
'   | 绑定                '
'   v                    '
' +--------------------+ '
' |       onBind       | '
' +--------------------+ '
'   |                    '
'   | 解绑                '
'   v                    '
' +--------------------+ '
' |      onUnbind      | '
' +--------------------+ '
'   |                    '
'   | 销毁                '
'   v                    '
' +--------------------+ '
' |     onDestroy      | '
' +--------------------+ '
'                        '
+ - - - - - - - - - - - -+
```

其中，对于startService方式来说，多次start会执行多次onStartCommand。
而bindService方式多次bind只会执行一次。
另外，service存在两种启动混用的情况，该情况的生命周期可见：

[任务4](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/blob/main/day2/Day2-Train4.md) 