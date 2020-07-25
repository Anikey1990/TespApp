package ru.anikey.feature_direction_impl.domain.interactors

import io.reactivex.Observable
import ru.anikey.core_database_api.data.models.OrdersDBModel
import ru.anikey.feature_direction_impl.data.repositories.TerminalsRepository
import ru.anikey.feature_direction_impl.domain.models.TerminalUIModel
import javax.inject.Inject

class DataBaseInteractor @Inject constructor(
    private val repository: TerminalsRepository
) {

    fun saveTerminals(terminals: List<TerminalUIModel>) = repository
        .saveTerminals(terminals)

    fun getTerminals(): Observable<List<TerminalUIModel>> = repository
        .loadTerminalsFromDataBase()

    fun saveOrder(ordersDBModel: OrdersDBModel) = repository
        .saveOrderToDataBase(order = ordersDBModel)

}
