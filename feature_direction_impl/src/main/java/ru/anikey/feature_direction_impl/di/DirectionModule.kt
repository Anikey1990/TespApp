package ru.anikey.feature_direction_impl.di

import dagger.Binds
import dagger.Module
import ru.anikey.feature_direction_api.FeatureDirectionStarter
import ru.anikey.feature_direction_impl.starter.FeatureDirectionStarterImpl

@Module
abstract class DirectionModule {

    @Binds
    @DirectionScope
    abstract fun bindDirectionFeatureStarter(starter: FeatureDirectionStarterImpl): FeatureDirectionStarter

}
