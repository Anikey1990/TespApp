package ru.anikey.core_database_impl.data.orders

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [OrdersEntity::class], version = 1)
abstract class OrdersDataBase : RoomDatabase() {
    abstract fun getOrdersDao(): OrdersDao
}
