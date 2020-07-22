package ru.anikey.core_database_impl.data.terminals

import io.reactivex.Completable
import io.reactivex.Observable
import ru.anikey.core_database_api.data.interfaces.TerminalsDBClient
import ru.anikey.core_database_api.data.models.TerminalsDBModel
import javax.inject.Inject

class TerminalsDBClientImpl @Inject constructor(
    dataBase: TerminalsDataBase
) : TerminalsDBClient {
    private val terminalsDao: TerminalsDao = dataBase.getTerminalsDao()

    override fun addTerminals(terminals: List<TerminalsDBModel>): Completable =
        terminalsDao.addTerminals(terminals = terminals.map { it.mapToEntity() })

    override fun getTerminals(): Observable<List<TerminalsDBModel>> = terminalsDao
        .getTerminals()
        .map {
            it.map { terminalsEntity ->
                terminalsEntity.mapToDBModel()
            }
        }

}
