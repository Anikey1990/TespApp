package ru.anikey.feature_direction_impl.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_select_terminal.view.*
import ru.anikey.feature_direction_impl.R
import ru.anikey.feature_direction_impl.presentation.adapters.SelectTerminalViewPagerAdapter

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

    lateinit var mViewPagerAdapter: SelectTerminalViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_select_terminal, container, false)

        initViews(view = root)

        return root
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

}
