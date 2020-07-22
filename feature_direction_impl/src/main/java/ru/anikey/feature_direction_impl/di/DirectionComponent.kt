package ru.anikey.feature_direction_impl.di

import dagger.Component
import ru.anikey.core_database_api.di.CoreDBClient
import ru.anikey.core_network_api.di.CoreNetworkApi
import ru.anikey.feature_direction_api.FeatureDirectionApi
import ru.anikey.feature_direction_api.FeatureDirectionDependencies
import ru.anikey.feature_direction_impl.presentation.fragments.MainFragment
import ru.anikey.feature_direction_impl.presentation.fragments.SelectTerminalFragment
import ru.anikey.feature_direction_impl.presentation.fragments.TerminalsListFragment

@DirectionScope
@Component(
    modules = [DirectionModule::class],
    dependencies = [FeatureDirectionDependencies::class]
)
abstract class DirectionComponent : FeatureDirectionApi {
    companion object {
        private var sDirectionComponent: DirectionComponent? = null

        fun initAndGet(dependencies: FeatureDirectionDependencies): FeatureDirectionApi {
            if (sDirectionComponent == null) {
                synchronized(DirectionComponent::class) {
                    if (sDirectionComponent == null) {
                        sDirectionComponent = DaggerDirectionComponent
                            .builder()
                            .featureDirectionDependencies(dependencies)
                            .build()
                    }
                }
            }
            return sDirectionComponent!!
        }

        fun get(): DirectionComponent = if (sDirectionComponent == null) {
            throw RuntimeException("${this::class.simpleName} is not init yet")
        } else sDirectionComponent!!
    }

    abstract fun inject(mainFragment: MainFragment)

    abstract fun inject(selectTerminalFragment: SelectTerminalFragment)

    abstract fun inject(terminalsListFragment: TerminalsListFragment)

    @DirectionScope
    @Component(dependencies = [CoreNetworkApi::class, CoreDBClient::class])
    interface DirectionDependenciesComponent : FeatureDirectionDependencies

}
