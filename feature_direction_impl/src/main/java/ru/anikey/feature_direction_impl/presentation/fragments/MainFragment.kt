package ru.anikey.feature_direction_impl.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_direction_main.view.*
import ru.anikey.feature_direction_impl.R
import ru.anikey.feature_direction_impl.di.DirectionComponent
import ru.anikey.feature_direction_impl.presentation.navigation.DirectionScreen
import ru.anikey.feature_direction_impl.presentation.viewmodels.MainViewModel
import ru.anikey.feature_direction_impl.presentation.viewstates.DirectionMainState
import ru.anikey.feature_direction_impl.starter.FeatureDirectionStarterImpl
import javax.inject.Inject

class MainFragment : Fragment(), LifecycleOwner {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    lateinit var mViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_direction_main, container, false)

        initInjection()
        initViewModel()
        initViews(view = root)
        subscribeData()

        if (!mViewModel.isTerminalsLoaded) mViewModel.getTerminals()

        return root
    }

    private fun initInjection() = DirectionComponent
        .get()
        .inject(mainFragment = this)

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this, mViewModelFactory)
            .get(MainViewModel::class.java)
        lifecycle.addObserver(mViewModel)
    }

    private fun initViews(view: View) = view.apply {
        fromField.setOnClickListener {
            FeatureDirectionStarterImpl.aRouter.navigateTo(
                DirectionScreen.SelectTerminal(
                    direction = SelectTerminalFragment.EnumDirection.FROM
                )
            )
        }

        toField.setOnClickListener {
            FeatureDirectionStarterImpl.aRouter.navigateTo(
                DirectionScreen.SelectTerminal(
                    direction = SelectTerminalFragment.EnumDirection.TO
                )
            )
        }
    }

    private fun subscribeData() {
        mViewModel.getViewState().observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is DirectionMainState.Loading -> setLoadingState()
                is DirectionMainState.Error -> setErrorState(throwable = state.throwable)
                is DirectionMainState.Success -> setSuccessState()
            }
        })
    }

    private fun setLoadingState() {
        requireView().mainProgressBar.visibility = View.VISIBLE
    }

    private fun setErrorState(throwable: Throwable) {
        requireView().mainProgressBar.visibility = View.GONE
        Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun setSuccessState() {
        requireView().apply {
            mainProgressBar.visibility = View.GONE
            fromField.setEnabled(value = true)
            toField.setEnabled(value = true)
        }
    }

}
