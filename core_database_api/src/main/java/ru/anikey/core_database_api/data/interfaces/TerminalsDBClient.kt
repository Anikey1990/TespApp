package ru.anikey.core_database_api.data.interfaces

import io.reactivex.Completable
import io.reactivex.Single
import ru.anikey.core_database_api.data.models.TerminalDBModel

interface TerminalsDBClient {

    fun addTerminals(terminals: List<TerminalDBModel>): Completable

    fun getTerminals(): Single<List<TerminalDBModel>>

}
