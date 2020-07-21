package ru.anikey.core_database_impl.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.anikey.core_database_api.data.interfaces.TerminalsDBClient
import ru.anikey.core_database_impl.data.terminals.TerminalsDBClientImpl
import ru.anikey.core_database_impl.data.terminals.TerminalsDataBase

@Module
class DataBaseModule(private val context: Context) {

    @Provides
    @DataBaseScope
    fun provideTerminalsDBClient(terminalsDataBase: TerminalsDataBase): TerminalsDBClient =
        TerminalsDBClientImpl(dataBase = terminalsDataBase)

    @Provides
    @DataBaseScope
    fun provideTerminalsDataBase(): TerminalsDataBase = Room.databaseBuilder(
        context,
        TerminalsDataBase::class.java,
        "terminals.db"
    ).build()

}
