package ru.anikey.core_injector

import android.content.Context
import ru.anikey.core_database_api.di.CoreDBClient
import ru.anikey.core_database_impl.di.DataBaseComponent
import ru.anikey.core_network_api.di.CoreNetworkApi
import ru.anikey.core_network_impl.di.NetworkComponent

object CoreInjector {

    fun injectCoreNetworkApi(): CoreNetworkApi = NetworkComponent.get()

    fun injectCoreDBClient(context: Context): CoreDBClient =
        DataBaseComponent.get(context = context)

}
