package ru.anikey.core_database_impl.data.orders

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.Completable

@Dao
interface OrdersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOrder(ordersEntity: OrdersEntity): Completable

}
