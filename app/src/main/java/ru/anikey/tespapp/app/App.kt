package ru.anikey.tespapp.app

import android.app.Application
import android.content.Context
import ru.anikey.tespapp.di.AppComponent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class App : Application() {

    companion object {
        private lateinit var sContext: Context
        private lateinit var cicerone: Cicerone<Router>
        lateinit var INSTANCE: App

        fun getAppContext(): Context = sContext
    }

    override fun onCreate() {
        super.onCreate()

        sContext = applicationContext
        cicerone = Cicerone.create()
        INSTANCE = this

        initInjection()
    }

    private fun initInjection() = AppComponent
        .get()
        .inject(app = this)

    fun getRouter(): Router = cicerone.router

    fun getNavigationHolder(): NavigatorHolder = cicerone.navigatorHolder

}
