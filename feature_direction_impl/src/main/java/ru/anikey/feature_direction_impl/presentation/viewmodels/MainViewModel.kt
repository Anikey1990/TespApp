package ru.anikey.feature_direction_impl.presentation.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.anikey.feature_direction_impl.domain.interactors.DataBaseInteractor
import ru.anikey.feature_direction_impl.domain.interactors.NetworkInteractor
import ru.anikey.feature_direction_impl.domain.models.TerminalUIModel
import ru.anikey.feature_direction_impl.presentation.viewstates.DirectionMainState

class MainViewModel(
    private val mDataBaseInteractor: DataBaseInteractor,
    private val mNetworkInteractor: NetworkInteractor
) : ViewModel(), LifecycleObserver {

    private val mDisposables = CompositeDisposable()
    private val mViewState = MutableLiveData<DirectionMainState>()
    var isTerminalsLoaded = false

    fun getViewState(): LiveData<DirectionMainState> = mViewState

    fun getTerminals() {
        mViewState.value = DirectionMainState.Loading
        val disposable = mNetworkInteractor.getTerminalsApiUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ terminals ->
                saveTerminalsToDB(terminals)
            }, {
                mViewState.postValue(DirectionMainState.Error(throwable = it))
            })
        mDisposables.add(disposable)
    }

    fun saveOrder() {

    }

    private fun saveTerminalsToDB(terminals: List<TerminalUIModel>) {
        val disposable = mDataBaseInteractor.saveTerminals(terminals)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mViewState.postValue(DirectionMainState.Success)
                isTerminalsLoaded = true
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
