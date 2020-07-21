package ru.anikey.feature_direction_api

import ru.anikey.core_database_api.di.CoreDBClient
import ru.anikey.core_network_api.di.CoreNetworkApi

interface FeatureDirectionDependencies {
    fun coreDataBaseClient(): CoreDBClient
    fun coreNetworkApi(): CoreNetworkApi
}
