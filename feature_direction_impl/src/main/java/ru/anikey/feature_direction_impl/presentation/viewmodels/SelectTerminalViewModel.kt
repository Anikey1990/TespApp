package ru.anikey.feature_direction_impl.presentation.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.anikey.feature_direction_impl.domain.interactors.DataBaseInteractor
import ru.anikey.feature_direction_impl.domain.models.TerminalUIModel
import ru.anikey.feature_direction_impl.presentation.viewstates.SelectTerminalViewState

class SelectTerminalViewModel(
    private val dataBaseInteractor: DataBaseInteractor
) : ViewModel(), LifecycleObserver {

    private val mDisposables = CompositeDisposable()
    private val mViewState = MutableLiveData<SelectTerminalViewState>()

    fun getViewState(): LiveData<SelectTerminalViewState> = mViewState

    fun getTerminals() {
        mViewState.value = SelectTerminalViewState.Loading
        val disposable = dataBaseInteractor.getTerminals()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                mViewState.postValue(
                    SelectTerminalViewState.Success(
                        terminalsFrom = getTerminalsReceiveCargo(source = it),
                        terminalsTo = getTerminalsGiveOutCargo(source = it)
                    )
                )
            }, {
                mViewState.postValue(SelectTerminalViewState.Error(throwable = it))
            })
        mDisposables.add(disposable)
    }

    private fun getTerminalsReceiveCargo(source: List<TerminalUIModel>): List<TerminalUIModel> {
        val result = mutableListOf<TerminalUIModel>()
        source.forEach { if (it.receiveCargo) result.add(it) }
        return result
    }

    private fun getTerminalsGiveOutCargo(source: List<TerminalUIModel>): List<TerminalUIModel> {
        val result = mutableListOf<TerminalUIModel>()
        source.forEach { if (it.giveoutCargo and it.isDefault) result.add(it) }
        return result
    }

    override fun onCleared() {
        mDisposables.dispose()
        super.onCleared()
    }

}
