package ru.anikey.feature_direction_impl.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.anikey.feature_direction_impl.domain.interactors.DataBaseInteractor
import ru.anikey.feature_direction_impl.domain.interactors.NetworkInteractor
import ru.anikey.feature_direction_impl.presentation.viewstates.DirectionMainState

class DirectionMainViewModel(
    private val mDataBaseInteractor: DataBaseInteractor,
    private val mNetworkInteractor: NetworkInteractor
) : ViewModel(), LifecycleObserver {

    private val mDisposables = CompositeDisposable()
    private val mViewState = MutableLiveData<DirectionMainState>()

    fun getViewState(): LiveData<DirectionMainState> = mViewState

    fun getTerminals() {
        mViewState.value = DirectionMainState.Loading
        val disposable = mNetworkInteractor.getTerminalsApiUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ terminals ->
                terminals.forEach {
                    Log.d("TERMINAL", it.name)
                }
            }, {
                mViewState.postValue(DirectionMainState.Error(throwable = it))
            })
        mDisposables.add(disposable)
    }

    override fun onCleared() {
        mDisposables.dispose()
        super.onCleared()
    }

}
