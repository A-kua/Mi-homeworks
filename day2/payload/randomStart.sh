#/bin/sh

# adb logcat 过滤规则为 package:mine level:debug tag=:fan.akua.day2.App

APP_PACKAGE="fan.akua.day2"
StandardActivity_CLASS="$APP_PACKAGE.StandardActivity"
SingleInstanceActivity_CLASS="$APP_PACKAGE.SingleInstanceActivity"
SingleTaskActivity_CLASS="$APP_PACKAGE.SingleTaskActivity"
SingleTopActivity_CLASS="$APP_PACKAGE.SingleTopActivity"

if ! command -v adb &> /dev/null
then
    echo "你的ADB呢？？"
    exit 1
fi

function stop_app() {
    adb shell am force-stop $1 &> /dev/null
    echo "force-stop : $APP_PACKAGE"
}

function startStander() {
    adb shell am start -n $APP_PACKAGE/$StandardActivity_CLASS &> /dev/null
    echo "stander"
}

function startSingleTask() {
    adb shell am start -n $APP_PACKAGE/$SingleInstanceActivity_CLASS &> /dev/null
    echo "singleTask"
}

function startSingleTaskActivity() {
    adb shell am start -n $APP_PACKAGE/$SingleTaskActivity_CLASS &> /dev/null
    echo "singleTask"
}
function startSingleTopActivity() {
    adb shell am start -n $APP_PACKAGE/$SingleTopActivity_CLASS &> /dev/null
    echo "singleTop"
}

stop_app $APP_PACKAGE

functions=(startStander startSingleTask startSingleTaskActivity startSingleTopActivity)
result=""
for i in {1..5}; do
    func_name=${functions[$((RANDOM % ${#functions[@]}))]}
    result+=$($func_name)
    sleep 1
    if [ $i -lt 4 ]; then
        result+=" - "
    fi
done

echo "执行顺序: $result"