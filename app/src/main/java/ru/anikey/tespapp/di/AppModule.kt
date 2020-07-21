package ru.anikey.tespapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.anikey.tespapp.app.App
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun getContext(): Context = App.getAppContext()

}
