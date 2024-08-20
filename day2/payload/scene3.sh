#!/bin/bash

PACKAGE_NAME="fan.akua.day2"
ACTIVITY_A="fan.akua.day2.launchMode.SingleTaskActivity"
ACTIVITY_B="fan.akua.day2.launchMode.DefaultActivity"

# 杀死已存在的进程
adb shell am force-stop $PACKAGE_NAME &> /dev/null

# 启动SingleTask的Activity A
adb shell am start -n $PACKAGE_NAME/$ACTIVITY_A

sleep 2
# 查看栈顶Activity
adb -d shell dumpsys activity activities | grep mResumedActivity

# 启动Activity B
adb shell am start -n $PACKAGE_NAME/$ACTIVITY_B

sleep 2
# 查看栈顶Activity
adb -d shell dumpsys activity activities | grep mResumedActivity

sleep 2
# 再次启动SingleTask的Activity A
adb shell am start -n $PACKAGE_NAME/$ACTIVITY_A

sleep 2
# 查看栈顶Activity
adb -d shell dumpsys activity activities | grep mResumedActivity