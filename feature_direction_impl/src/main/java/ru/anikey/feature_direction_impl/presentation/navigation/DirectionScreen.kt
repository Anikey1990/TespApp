package ru.anikey.feature_direction_impl.presentation.navigation

import androidx.fragment.app.Fragment
import ru.anikey.feature_direction_impl.presentation.fragments.MainFragment
import ru.anikey.feature_direction_impl.presentation.fragments.SelectTerminalFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class DirectionScreen : SupportAppScreen() {
    class Main : DirectionScreen() {
        override fun getFragment(): Fragment = MainFragment()
    }

    class SelectTerminal(private val direction: SelectTerminalFragment.EnumDirection) :
        DirectionScreen() {
        override fun getFragment(): Fragment =
            SelectTerminalFragment.getInstance(direction = direction)
    }
}