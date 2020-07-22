package ru.anikey.feature_direction_impl.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_select_terminal.view.*
import ru.anikey.feature_direction_impl.R
import ru.anikey.feature_direction_impl.di.DirectionComponent
import ru.anikey.feature_direction_impl.domain.models.TerminalUIModel
import ru.anikey.feature_direction_impl.presentation.adapters.SelectTerminalViewPagerAdapter
import ru.anikey.feature_direction_impl.presentation.viewmodels.SelectTerminalViewModel
import ru.anikey.feature_direction_impl.presentation.viewstates.SelectTerminalViewState
import javax.inject.Inject

class SelectTerminalFragment : Fragment() {

    enum class EnumDirection { FROM, TO }

    private lateinit var direction: EnumDirection

    companion object {
        fun getInstance(direction: EnumDirection): SelectTerminalFragment {
            val fragment = SelectTerminalFragment()
            fragment.direction = direction
            return fragment
        }
    }

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    private lateinit var mViewModel: SelectTerminalViewModel

    private lateinit var mViewPagerAdapter: SelectTerminalViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_select_terminal, container, false)

        initInjection()
        initViews(view = root)
        initViewModel()
        subscribeData()

        mViewModel.getTerminals()

        return root
    }

    private fun initInjection() = DirectionComponent
        .get()
        .inject(selectTerminalFragment = this)

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this, mViewModelFactory)
            .get(SelectTerminalViewModel::class.java)
        lifecycle.addObserver(mViewModel)
    }

    private fun initViews(view: View) {
        mViewPagerAdapter = SelectTerminalViewPagerAdapter(fragmentManager = childFragmentManager)
        mViewPagerAdapter.apply {
            if (count == 0) {
                addFragment(title = "Откуда", fragment = TerminalsListFragment())
                addFragment(title = "Куда", fragment = TerminalsListFragment())

                view.viewPager.adapter = mViewPagerAdapter
                view.tabLayout.setupWithViewPager(view.viewPager)
            }
        }
        when (direction) {
            EnumDirection.FROM -> view.tabLayout.getTabAt(0)?.select()
            EnumDirection.TO -> view.tabLayout.getTabAt(1)?.select()
        }
    }

    private fun subscribeData() {
        mViewModel.getViewState().observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is SelectTerminalViewState.Loading -> setLoadingState()
                is SelectTerminalViewState.Error -> setErrorState(throwable = state.throwable)
                is SelectTerminalViewState.Success -> setSuccessState(
                    terminalsFrom = state.terminalsFrom,
                    terminalsTo = state.terminalsTo
                )
            }
        })
    }

    private fun setLoadingState() {

    }

    private fun setErrorState(throwable: Throwable) {
        Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun setSuccessState(
        terminalsFrom: List<TerminalUIModel>,
        terminalsTo: List<TerminalUIModel>
    ) {
        with(mViewPagerAdapter.getItem(0) as TerminalsListFragment) {
            this.fetchTerminals(data = terminalsFrom)
        }

        with(mViewPagerAdapter.getItem(1) as TerminalsListFragment) {
            this.fetchTerminals(data = terminalsTo)
        }
    }

}
