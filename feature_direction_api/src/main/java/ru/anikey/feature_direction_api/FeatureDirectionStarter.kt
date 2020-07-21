package ru.anikey.feature_direction_api

import ru.terrakok.cicerone.Router

interface FeatureDirectionStarter {
    fun startFeature(router: Router)
}
