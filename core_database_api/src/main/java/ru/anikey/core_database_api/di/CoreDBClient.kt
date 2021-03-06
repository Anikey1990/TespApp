package ru.anikey.core_database_api.di

import ru.anikey.core_database_api.data.interfaces.OrdersDBClient
import ru.anikey.core_database_api.data.interfaces.TerminalsDBClient

interface CoreDBClient {

    fun terminalsDBClient(): TerminalsDBClient

    fun ordersDBClient(): OrdersDBClient
}
