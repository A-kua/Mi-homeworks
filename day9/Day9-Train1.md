## Day8-Train1

相关的文件如下：

1. [CameraActivity.kt](app/src/main/java/fan/akua/day9/activities/CameraActivity.kt)

好久没写kt了，写一下kt吧，正好用一下ActivityResult的API。

### 编写代码

权限请求回调

```kotlin
private val cameraPermissionLauncher = 
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            Log.e("simon", "back $isGranted")
            if (isGranted) {

                val holder = binding.surface.holder
                holder.setFormat(PixelFormat.TRANSPARENT);
                holder.addCallback(object : SurfaceHolder.Callback {
                    override fun surfaceCreated(holder: SurfaceHolder) {
                        Log.e("simon", "create $isGranted")
                        mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                        mCamera.setPreviewDisplay(holder)
                        mCamera.startPreview()
                    }

                    override fun surfaceChanged(
                        holder: SurfaceHolder,
                        format: Int,
                        width: Int,
                        height: Int
                    ) {

                    }

                    override fun surfaceDestroyed(holder: SurfaceHolder) {
                        mCamera.stopPreview();
                        mCamera.release();
                    }

                })
                binding.surface.setVisibility(View.VISIBLE);
            } else {
                Snackbar.make(window.decorView, "给我权限啊", Snackbar.LENGTH_SHORT).show()
            }
        }
    
```

权限请求处理

```kotlin
binding.take.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Snackbar.make(it, "没授权呢", Snackbar.LENGTH_SHORT).show()
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            } else {
                mCamera.autoFocus(object : Camera.AutoFocusCallback {
                    override fun onAutoFocus(success: Boolean, camera: Camera?) {
                        mCamera.takePicture(null, null, object : Camera.PictureCallback {
                            override fun onPictureTaken(data: ByteArray?, camera: Camera?) {
                                Snackbar.make(it, "拍照了，但是介于存储权限难于申请，这里就不写入了", Snackbar.LENGTH_SHORT).show()
                            }
                        })
                    }
                });
            }
        }
```

### 运行效果如下

[视频无法播放请点击我](vx_images/Screen_recording_20240827_095901.mp4)

<div>
    <video src="vx_images/Screen_recording_20240827_095901.mp4"></video>
</div>
