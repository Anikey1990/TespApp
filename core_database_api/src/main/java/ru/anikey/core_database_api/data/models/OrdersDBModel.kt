package ru.anikey.core_database_api.data.models

data class OrdersDBModel(
    val orderId: Int?,
    val terminalFromId: Int,
    val terminalToId: Int
)
