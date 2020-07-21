package ru.anikey.core_database_impl.data.terminals

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TerminalsEntity::class], version = 1)
abstract class TerminalsDataBase : RoomDatabase() {
    abstract fun getTerminalsDao(): TerminalsDao
}
