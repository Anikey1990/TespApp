package ru.anikey.feature_direction_impl.presentation.navigation

import androidx.fragment.app.Fragment
import ru.anikey.feature_direction_impl.presentation.fragments.DirectionMainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class DirectionScreen : SupportAppScreen() {
    class Main : DirectionScreen() {
        override fun getFragment(): Fragment = DirectionMainFragment()
    }
}