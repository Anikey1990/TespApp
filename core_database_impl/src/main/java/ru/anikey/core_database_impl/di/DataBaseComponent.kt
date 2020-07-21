package ru.anikey.core_database_impl.di

import android.content.Context
import dagger.Component
import ru.anikey.core_database_api.di.CoreDBClient

@DataBaseScope
@Component(modules = [DataBaseModule::class])
abstract class DataBaseComponent : CoreDBClient {

    companion object {
        private var sDataBaseComponent: DataBaseComponent? = null

        fun get(context: Context): CoreDBClient {
            if (sDataBaseComponent == null) {
                synchronized(DataBaseComponent::class.java) {
                    if (sDataBaseComponent == null) {
                        sDataBaseComponent = DaggerDataBaseComponent
                            .builder()
                            .dataBaseModule(DataBaseModule(context))
                            .build()
                    }
                }
            }

            return sDataBaseComponent!!
        }
    }

    fun resetComponent() {
        sDataBaseComponent = null
    }

}
