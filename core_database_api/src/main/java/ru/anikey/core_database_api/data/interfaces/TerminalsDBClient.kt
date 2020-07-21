package ru.anikey.core_database_api.data.interfaces

import io.reactivex.Completable
import io.reactivex.Single
import ru.anikey.core_database_api.data.models.TerminalsDBModel

interface TerminalsDBClient {

    fun addTerminals(terminals: List<TerminalsDBModel>): Completable

    fun getTerminals(): Single<List<TerminalsDBModel>>

}
