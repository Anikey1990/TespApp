package ru.anikey.core_database_impl.data.terminals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface TerminalsDao {

    @Query("SELECT * FROM TerminalsEntity")
    fun getTerminals(): Observable<List<TerminalsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTerminals(terminals: List<TerminalsEntity>): Completable

}
