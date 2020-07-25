package ru.anikey.feature_direction_impl.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_select_terminal.view.*
import ru.anikey.feature_direction_impl.R
import ru.anikey.feature_direction_impl.di.DirectionComponent
import ru.anikey.feature_direction_impl.domain.models.TerminalUIModel
import ru.anikey.feature_direction_impl.presentation.adapters.TerminalsListAdapter
import ru.anikey.feature_direction_impl.presentation.managers.OrderManager
import ru.anikey.feature_direction_impl.presentation.viewmodels.SelectTerminalViewModel
import ru.anikey.feature_direction_impl.presentation.viewstates.SelectTerminalViewState
import ru.anikey.feature_direction_impl.starter.FeatureDirectionStarterImpl
import javax.inject.Inject

class SelectTerminalFragment : Fragment() {

    enum class EnumDirection { FROM, TO }

    private lateinit var direction: EnumDirection
    private val terminalsFrom = mutableListOf<TerminalUIModel>()
    private val terminalsTo = mutableListOf<TerminalUIModel>()

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

    @Inject
    lateinit var mAdapter: TerminalsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_select_terminal, container, false)

        initInjection()
        initToolbar()
        initTabs(view = root)
        initRecycler(view = root)
        initViewModel()
        subscribeData()

        mViewModel.getTerminals()

        return root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                return true
            }
        }
        return false
    }

    private fun initInjection() = DirectionComponent
        .get()
        .inject(selectTerminalFragment = this)

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this, mViewModelFactory)
            .get(SelectTerminalViewModel::class.java)
        lifecycle.addObserver(mViewModel)
    }

    private fun initToolbar() {
        with(requireActivity() as AppCompatActivity) {
            this.supportActionBar?.apply {
                title = getString(R.string.select_terminal_toolbar_title)
                setHasOptionsMenu(true)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    private fun initTabs(view: View) {
        view.tabLayout.apply {
            addTab(newTab())
            addTab(newTab())
            getTabAt(0)?.apply {
                text = getString(R.string.main_from_title)
                if (direction == EnumDirection.FROM) select()
            }
            getTabAt(1)?.apply {
                text = getString(R.string.main_to_title)
                if (direction == EnumDirection.TO) select()
            }

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {}

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            direction = EnumDirection.FROM
                            mAdapter.fetchData(terminals = terminalsFrom)
                        }
                        1 -> {
                            direction = EnumDirection.TO
                            mAdapter.fetchData(terminals = terminalsTo)
                        }
                    }
                }
            })
        }
    }

    private fun initRecycler(view: View) {
        mAdapter.setOnClickListener(onTerminalClickListener = object :
            TerminalsListAdapter.OnTerminalClickListener {

            override fun onTerminalClicked(terminal: TerminalUIModel) {
                when (direction) {
                    EnumDirection.FROM -> {
                        OrderManager.terminalFrom = terminal
                        FeatureDirectionStarterImpl.aRouter.exit()
                    }
                    EnumDirection.TO -> {
                        OrderManager.terminalTo = terminal
                        FeatureDirectionStarterImpl.aRouter.exit()
                    }
                }
            }
        })
        view.terminalsList.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            setHasFixedSize(true)
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

    private fun setLoadingState() = requireView().apply {
        terminalsProgressBar.visibility = View.VISIBLE
        terminalsList.visibility = View.GONE
    }

    private fun setErrorState(throwable: Throwable) {
        requireView().terminalsProgressBar.visibility = View.GONE
        Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun setSuccessState(
        terminalsFrom: List<TerminalUIModel>,
        terminalsTo: List<TerminalUIModel>
    ) {
        this.terminalsFrom.apply {
            clear()
            addAll(terminalsFrom)
        }
        this.terminalsTo.apply {
            clear()
            addAll(terminalsTo)
        }
        when (direction) {
            EnumDirection.FROM -> mAdapter.fetchData(this.terminalsFrom)
            EnumDirection.TO -> mAdapter.fetchData(this.terminalsTo)
        }

        requireView().apply {
            terminalsProgressBar.visibility = View.GONE
            terminalsList.visibility = View.VISIBLE
        }
    }

}
