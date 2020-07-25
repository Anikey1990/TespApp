package ru.anikey.feature_direction_impl.data.repositories

import io.reactivex.Observable
import io.reactivex.Single
import ru.anikey.core_database_api.data.models.OrdersDBModel
import ru.anikey.core_database_api.di.CoreDBClient
import ru.anikey.core_network_api.di.CoreNetworkApi
import ru.anikey.feature_direction_impl.domain.models.TerminalUIModel
import ru.anikey.feature_direction_impl.domain.models.mapToUI
import javax.inject.Inject

class TerminalsRepository @Inject constructor(
    private val coreNetworkApi: CoreNetworkApi,
    private val coreDBClient: CoreDBClient
) {

    fun loadTerminalsFromNetwork(): Single<List<TerminalUIModel>> = coreNetworkApi
        .terminalsApiClient()
        .makeTerminalsRequest()
        .map { it.mapToUI() }

    fun loadTerminalsFromDataBase(): Observable<List<TerminalUIModel>> = coreDBClient
        .terminalsDBClient()
        .getTerminals()
        .map { terminalsList ->
            terminalsList.map { it.mapToUI() }
        }

    fun saveTerminals(terminals: List<TerminalUIModel>) = coreDBClient
        .terminalsDBClient()
        .addTerminals(terminals = terminals.map { it.mapToDB() })

    fun saveOrderToDataBase(order: OrdersDBModel) = coreDBClient
        .ordersDBClient()
        .saveOrder(ordersDBModel = order)

}
