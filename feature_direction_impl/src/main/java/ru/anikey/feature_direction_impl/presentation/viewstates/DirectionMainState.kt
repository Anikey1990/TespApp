package ru.anikey.feature_direction_impl.presentation.viewstates

sealed class DirectionMainState {
    object Loading : DirectionMainState()
    object Success : DirectionMainState()
    class Error(val throwable: Throwable) : DirectionMainState()
}
