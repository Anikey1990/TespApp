package ru.anikey.feature_direction_impl.domain.interactors

import io.reactivex.Single
import ru.anikey.feature_direction_impl.data.repositories.TerminalsRepository
import ru.anikey.feature_direction_impl.domain.models.TerminalUIModel
import javax.inject.Inject

class NetworkInteractor @Inject constructor(
    private val repository: TerminalsRepository
) {

    fun getTerminalsApiUseCase(): Single<List<TerminalUIModel>> = repository
        .loadTerminalsFromNetwork()

}
