package ru.anikey.feature_direction_impl.presentation.viewstates

import ru.anikey.feature_direction_impl.domain.models.TerminalUIModel

sealed class SelectTerminalViewState {
    object Loading : SelectTerminalViewState()
    class Error(val throwable: Throwable) : SelectTerminalViewState()
    class Success(
        val terminalsFrom: List<TerminalUIModel>,
        val terminalsTo: List<TerminalUIModel>
    ) : SelectTerminalViewState()
}
