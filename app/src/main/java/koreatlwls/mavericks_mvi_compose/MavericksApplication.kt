package koreatlwls.mavericks_mvi_compose

import android.app.Application
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MavericksApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Mavericks.initialize(this)
    }

}