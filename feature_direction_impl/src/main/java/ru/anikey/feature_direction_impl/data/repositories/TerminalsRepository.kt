package ru.anikey.feature_direction_impl.data.repositories

import io.reactivex.Single
import ru.anikey.core_database_api.di.CoreDBClient
import ru.anikey.core_network_api.di.CoreNetworkApi
import ru.anikey.feature_direction_impl.domain.models.TerminalsUIModel
import ru.anikey.feature_direction_impl.domain.models.mapToUI
import javax.inject.Inject

class TerminalsRepository @Inject constructor(
    private val coreNetworkApi: CoreNetworkApi,
    val coreDBClient: CoreDBClient
) {

    fun loadTerminalsFromNetwork(): Single<List<TerminalsUIModel>> = coreNetworkApi
        .terminalsApiClient()
        .makeTerminalsRequest()
        .map { it.mapToUI() }

}
