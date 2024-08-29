package fan.akua.day11

import android.app.Application
import com.google.android.material.color.DynamicColors
import fan.akua.day11.api.impl.LocalRepository

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}