package ru.anikey.core_database_api.data.interfaces

import io.reactivex.Completable
import ru.anikey.core_database_api.data.models.OrdersDBModel

interface OrdersDBClient {

    fun saveOrder(ordersDBModel: OrdersDBModel): Completable

}
