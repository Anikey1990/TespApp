package ru.anikey.feature_direction_impl.presentation.viewstates

import ru.anikey.feature_direction_impl.domain.models.TerminalsUIModel

sealed class DirectionMainState {
    object Loading : DirectionMainState()
    class Error(val throwable: Throwable) : DirectionMainState()
    class Success(val terminals: List<TerminalsUIModel>) : DirectionMainState()
}
