package ru.anikey.feature_direction_impl.starter

import ru.anikey.feature_direction_api.FeatureDirectionStarter
import ru.anikey.feature_direction_impl.presentation.navigation.DirectionScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FeatureDirectionStarterImpl @Inject constructor() : FeatureDirectionStarter {

    override fun startFeature(router: Router) {
        router.newRootScreen(DirectionScreen.Main())
    }

}
