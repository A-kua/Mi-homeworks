## Day5-Train4

相关的文件如下：
1. [ConstraintActivity.java](app/src/main/java/fan/akua/day5/activities/ConstraintActivity.java)
2. [activity_constraint.xml](app/src/main/res/layout/activity_constraint.xml)

### 编写布局

没啥说的，对着做

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/img"
        android:layout_width="24dp"
        android:layout_height="24dp"
        android:layout_margin="6dp"
        android:padding="6dp"
        android:src="@drawable/baseline_arrow_back_ios_new_24"
        app:layout_constraintBottom_toTopOf="@id/guideline3"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/textView4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="6dp"
        android:text="测试一下登录页面"
        app:layout_constraintBottom_toTopOf="@id/guideline3"
        app:layout_constraintStart_toEndOf="@id/img"
        app:layout_constraintTop_toTopOf="parent" />

    <ImageView
        android:id="@+id/imageView2"
        android:layout_width="24dp"
        android:layout_height="24dp"
        android:layout_margin="6dp"
        android:padding="6dp"
        android:src="@drawable/ic_android_black_24dp"
        app:layout_constraintBottom_toTopOf="@id/guideline3"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintGuide_percent="0.07" />

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="72dp"
        android:layout_height="72dp"
        android:layout_marginTop="100dp"
        android:src="@mipmap/ic_launcher"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/guideline3" />

    <TextView
        android:id="@+id/textView5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="12dp"
        android:text="这是输入你的账号吧"
        app:layout_constraintEnd_toEndOf="@+id/imageView"
        app:layout_constraintStart_toStartOf="@+id/imageView"
        app:layout_constraintTop_toBottomOf="@+id/imageView" />

    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/inputLayout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="12dp"
        android:hint="请输入手机号"
        android:minEms="11"
        app:counterEnabled="true"
        app:counterMaxLength="11"
        app:layout_constraintEnd_toEndOf="@+id/textView5"
        app:layout_constraintStart_toStartOf="@+id/textView5"
        app:layout_constraintTop_toBottomOf="@+id/textView5">

        <com.google.android.material.textfield.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </com.google.android.material.textfield.TextInputLayout>

    <com.google.android.material.button.MaterialButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="登录"
        app:layout_constraintEnd_toEndOf="@id/inputLayout"
        app:layout_constraintStart_toStartOf="@id/inputLayout"
        app:layout_constraintTop_toBottomOf="@id/inputLayout" />

    <TextView
        android:id="@+id/tv1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="6dp"
        android:text="同意《隐私协议》"
        app:layout_constraintBottom_toTopOf="@id/declare"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

    <TextView
        android:id="@+id/declare"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="12dp"
        android:text="遇到问题"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

    <ImageView
        android:id="@+id/img2"
        android:layout_width="24dp"
        android:layout_height="24dp"
        android:padding="6dp"
        android:src="@drawable/baseline_arrow_circle_left_24"
        app:layout_constraintBottom_toBottomOf="@id/declare"
        app:layout_constraintEnd_toStartOf="@id/declare"
        app:layout_constraintTop_toTopOf="@id/declare" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### 实现跳转

![Activity](vx_images/114666220035039.png)

### 运行效果如下

![运行](vx_images/288686963182730.png)