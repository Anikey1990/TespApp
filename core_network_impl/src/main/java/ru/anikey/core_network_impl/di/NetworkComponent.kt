package ru.anikey.core_network_impl.di

import dagger.Component
import ru.anikey.core_network_api.di.CoreNetworkApi

@NetworkScope
@Component(modules = [NetworkModule::class])
abstract class NetworkComponent : CoreNetworkApi {

    companion object {
        private var sNetworkComponent: NetworkComponent? = null

        fun get(): CoreNetworkApi {
            if (sNetworkComponent == null) {
                synchronized(NetworkComponent::class) {
                    if (sNetworkComponent == null) {
                        sNetworkComponent = DaggerNetworkComponent
                            .builder()
                            .networkModule(NetworkModule())
                            .build()
                    }
                }
            }
            return sNetworkComponent!!
        }
    }

    fun resetComponent() {
        sNetworkComponent = null
    }

}
