package ru.anikey.core_database_impl.data.orders

import io.reactivex.Completable
import ru.anikey.core_database_api.data.interfaces.OrdersDBClient
import ru.anikey.core_database_api.data.models.OrdersDBModel
import javax.inject.Inject

class OrdersDBClientImpl @Inject constructor(
    dataBase: OrdersDataBase
) : OrdersDBClient {
    private val ordersDao: OrdersDao = dataBase.getOrdersDao()

    override fun saveOrder(ordersDBModel: OrdersDBModel): Completable = ordersDao
        .saveOrder(ordersEntity = ordersDBModel.mapToEntity())

}
