package ru.anikey.feature_direction_impl.domain.interactors

import ru.anikey.feature_direction_impl.data.repositories.TerminalsRepository
import ru.anikey.feature_direction_impl.domain.models.TerminalsUIModel
import javax.inject.Inject

class DataBaseInteractor @Inject constructor(
    private val repository: TerminalsRepository
) {

    fun saveTerminals(terminals: List<TerminalsUIModel>) = repository
        .saveTerminals(terminals)

}
