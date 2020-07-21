package ru.anikey.core_injector

import ru.anikey.core_network_api.di.CoreNetworkApi
import ru.anikey.core_network_impl.di.NetworkComponent

object CoreInjector {

    fun injectCoreNetworkApi(): CoreNetworkApi = NetworkComponent.get()

}
