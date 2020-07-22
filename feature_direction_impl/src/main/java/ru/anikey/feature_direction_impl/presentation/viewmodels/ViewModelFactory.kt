package ru.anikey.feature_direction_impl.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.anikey.feature_direction_impl.domain.interactors.DataBaseInteractor
import ru.anikey.feature_direction_impl.domain.interactors.NetworkInteractor
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val mDataBaseInteractor: DataBaseInteractor,
    private val mNetworkInteractor: NetworkInteractor
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                return MainViewModel(mDataBaseInteractor, mNetworkInteractor) as T
            }
        }
        throw ClassNotFoundException("${modelClass.simpleName} is not found")
    }

}
