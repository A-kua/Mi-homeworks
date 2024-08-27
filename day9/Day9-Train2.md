## Day8-Train2

相关的文件如下：

1. [ContactActivity.kt](app/src/main/java/fan/akua/day9/activities/ContactActivity.kt)

好久没写kt了，写一下kt吧，正好用一下ActivityResult的API。

### 编写代码

主要的请求处理

```kotlin
private val groupPermissionLauncher by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val readContactsGranted = permissions[Manifest.permission.READ_CONTACTS] ?: false
            val writeContactsGranted = permissions[Manifest.permission.WRITE_CONTACTS] ?: false

            if (readContactsGranted && writeContactsGranted) {
                Snackbar.make(binding.root, "授权了", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private val signalPermissionLauncher by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Snackbar.make(binding.root, "授权了", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
```

### 运行效果如下

[视频无法播放请点击我](vx_images/Screen_recording_20240827_100157.mp4)

<div>
    <video src="vx_images/Screen_recording_20240827_100157.mp4"></video>
</div>
