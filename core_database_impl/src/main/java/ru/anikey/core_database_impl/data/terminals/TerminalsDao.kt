package ru.anikey.core_database_impl.data.terminals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TerminalsDao {

    @Query("SELECT * FROM terminalsentity")
    fun getTerminals(): Single<List<TerminalsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTerminals(terminals: List<TerminalsEntity>): Completable

}
