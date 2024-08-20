## Day2-Train1

相关的文件如下：
1. [App.java](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/blob/main/day2/app/src/main/java/fan/akua/day2/App.java)
2. [ServiceControllerActivity.java](https://partner-gitlab.mioffice.cn/nj-trainingcollege/miclassroom240819/androidgroup4/tanzhehao/homework/-/blob/main/day2/app/src/main/java/fan/akua/day2/activities/ServiceControllerActivity.java)

### 直接在Application中注册Activity周期回调

![App.java](pic/49153258467602.png)

### 在清单中声明Activity和Application

![清单](pic/597571664544483.png)

### 使用ADB启动指定Activity

`adb shell am start -n fan.akua.day2/fan.akua.day2.ServiceControllerActivity`

![shell](pic/167794501281329.png)

### 观察日志

`package:mine level:debug tag=:fan.akua.day2.App`

![日志](pic/577307843543622.png)

此时回到桌面

![日志](pic/199269908767109.png)

再返回程序

![日志](pic/342518993218934.png)

用返回键退出程序

![日志](pic/500006711705052.png)

### 小结

Activity的生命周期如下

```text
+ - - - - - - - - - - -+
'  Activity LifeCycle  '
'                      '
' +------------------+ '
' |     onCreate     | '
' +------------------+ '
'   |                  '
'   | 可见               '
'   v                  '
' +------------------+ '
' |     onStart      | '
' +------------------+ '
'   |                  '
'   | 可交换              '
'   v                  '
' +------------------+ '
' |     onResume     | '
' +------------------+ '
'   |                  '
'   | 不可交互             '
'   v                  '
' +------------------+ '
' |     onPause      | '
' +------------------+ '
'   |                  '
'   | 不可见              '
'   v                  '
' +------------------+ '
' |      onStop      | '
' +------------------+ '
'   |                  '
'   | 销毁               '
'   v                  '
' +------------------+ '
' |    onDestroy     | '
' +------------------+ '
'                      '
+ - - - - - - - - - - -+

```