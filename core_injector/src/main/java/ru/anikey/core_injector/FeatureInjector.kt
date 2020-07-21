package ru.anikey.core_injector

import android.content.Context
import ru.anikey.feature_direction_api.FeatureDirectionApi
import ru.anikey.feature_direction_impl.di.DaggerDirectionComponent_DirectionDependenciesComponent
import ru.anikey.feature_direction_impl.di.DirectionComponent

object FeatureInjector {

    fun getDirectionFeature(context: Context): FeatureDirectionApi = DirectionComponent.initAndGet(
        dependencies = DaggerDirectionComponent_DirectionDependenciesComponent
            .builder()
            .coreDBClient(CoreInjector.injectCoreDBClient(context))
            .coreNetworkApi(CoreInjector.injectCoreNetworkApi())
            .build()
    )

}
