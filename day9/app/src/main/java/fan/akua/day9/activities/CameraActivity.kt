package fan.akua.day9.activities

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.PixelFormat
import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import fan.akua.day9.databinding.ActivityMainBinding
import kotlin.math.log

class CameraActivity : AppCompatActivity() {
    private lateinit var mCamera: Camera
    private val cameraPermissionLauncher by lazy {
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
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 初始化相机权限请求
        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)

        binding.take.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Snackbar.make(it, "没授权呢", Snackbar.LENGTH_SHORT).show()
            } else {
                mCamera.autoFocus(object : Camera.AutoFocusCallback {
                    override fun onAutoFocus(success: Boolean, camera: Camera?) {
                        mCamera.takePicture(null, null, object : Camera.PictureCallback {
                            override fun onPictureTaken(data: ByteArray?, camera: Camera?) {

                            }
                        })
                    }
                });
            }
        }
    }

}
