package fan.akua.day11.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest
import kotlin.random.Random

class RandomBlurTransformation(private val context: Context) : BitmapTransformation() {

    private var blurRadius: Float = Random.nextInt(1, 25).toFloat()

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update("RandomBlurTransformation".toByteArray())
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        return BlurUtils.blur(context, toTransform, blurRadius)
    }
}