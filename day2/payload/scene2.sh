#!/bin/bash

PACKAGE_NAME="fan.akua.day2"
ACTIVITY_A="fan.akua.day2.launchMode.SingleTopActivity"

# 杀死已存在的进程
adb shell am force-stop $PACKAGE_NAME &> /dev/null

# 启动SingleTop的Activity A
adb shell am start -n $PACKAGE_NAME/$ACTIVITY_A

sleep 2
# 查看栈顶Activity
adb -d shell dumpsys activity activities | grep mResumedActivity

# 再次启动SingleTop的Activity A
adb shell am start -n $PACKAGE_NAME/$ACTIVITY_A

sleep 2
# 查看栈顶Activity
adb -d shell dumpsys activity activities | grep mResumedActivity