package ru.anikey.feature_direction_impl.starter

import ru.anikey.feature_direction_api.FeatureDirectionStarter
import ru.anikey.feature_direction_impl.presentation.navigation.DirectionScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FeatureDirectionStarterImpl @Inject constructor() : FeatureDirectionStarter {

    companion object {
        lateinit var aRouter: Router
    }

    override fun startFeature(router: Router) {
        aRouter = router
        aRouter.newRootScreen(DirectionScreen.Main())
    }

}
