package ru.anikey.tespapp.di

import android.content.Context
import dagger.Component
import ru.anikey.tespapp.app.App
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
abstract class AppComponent {

    companion object {
        private var sAppComponent: AppComponent? = null

        fun get(): AppComponent {
            if (sAppComponent == null) {
                synchronized(AppComponent::class) {
                    if (sAppComponent == null) {
                        sAppComponent = DaggerAppComponent
                            .builder()
                            .build()
                    }
                }
            }
            return sAppComponent!!
        }
    }

    abstract fun inject(app: App)

    abstract fun getAppContext(): Context

}
