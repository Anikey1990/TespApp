package ru.anikey.core_database_impl.data.orders

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.anikey.core_database_api.data.models.OrdersDBModel

@Entity
data class OrdersEntity(
    @PrimaryKey(autoGenerate = true) val orderId: Int?,
    val terminalFrom: Int,
    val terminalTo: Int
)

fun OrdersDBModel.mapToEntity(): OrdersEntity = OrdersEntity(
    orderId = orderId,
    terminalFrom = terminalFromId,
    terminalTo = terminalToId
)
