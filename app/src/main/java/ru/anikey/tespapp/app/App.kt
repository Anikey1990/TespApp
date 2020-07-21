package ru.anikey.tespapp.app

import android.app.Application
import android.content.Context
import ru.anikey.tespapp.di.AppComponent

class App : Application() {

    companion object {
        private lateinit var sContext: Context

        fun getAppContext(): Context = sContext
    }

    override fun onCreate() {
        super.onCreate()

        sContext = applicationContext

        initInjection()
    }

    private fun initInjection() = AppComponent
        .get()
        .inject(app = this)

}
