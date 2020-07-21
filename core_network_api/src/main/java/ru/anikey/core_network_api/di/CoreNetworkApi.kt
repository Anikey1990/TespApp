package ru.anikey.core_network_api.di

import ru.anikey.core_network_api.data.interfaces.TerminalsApiClient

interface CoreNetworkApi {

    fun terminalsApiClient(): TerminalsApiClient

}
