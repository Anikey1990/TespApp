package ru.anikey.feature_direction_impl.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.anikey.feature_direction_api.FeatureDirectionStarter
import ru.anikey.feature_direction_impl.presentation.viewmodels.ViewModelFactory
import ru.anikey.feature_direction_impl.starter.FeatureDirectionStarterImpl

@Module
abstract class DirectionModule {

    @Binds
    @DirectionScope
    abstract fun bindDirectionFeatureStarter(starter: FeatureDirectionStarterImpl): FeatureDirectionStarter

    @Binds
    @DirectionScope
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}
