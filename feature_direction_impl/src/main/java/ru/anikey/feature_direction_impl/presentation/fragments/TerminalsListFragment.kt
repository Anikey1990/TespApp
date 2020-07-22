package ru.anikey.feature_direction_impl.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_terminals_list.view.*
import ru.anikey.feature_direction_impl.R
import ru.anikey.feature_direction_impl.di.DirectionComponent
import ru.anikey.feature_direction_impl.domain.models.TerminalUIModel
import ru.anikey.feature_direction_impl.presentation.adapters.TerminalsListAdapter
import javax.inject.Inject

class TerminalsListFragment : Fragment() {

    @Inject
    lateinit var mAdapter: TerminalsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_terminals_list, container, false)

        initInjection()
        initViews(view = root)

        return root
    }

    fun fetchTerminals(data: List<TerminalUIModel>) = mAdapter.fetchData(terminals = data)

    private fun initInjection() = DirectionComponent
        .get()
        .inject(terminalsListFragment = this)

    private fun initViews(view: View) {
        view.terminalsList.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
    }

}
