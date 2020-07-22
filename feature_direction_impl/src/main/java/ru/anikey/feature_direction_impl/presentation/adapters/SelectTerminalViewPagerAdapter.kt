package ru.anikey.feature_direction_impl.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SelectTerminalViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mTitles = mutableListOf<String>()
    private val mFragments = mutableListOf<Fragment>()

    override fun getItem(position: Int): Fragment = mFragments[position]
    override fun getCount(): Int = mFragments.count()
    override fun getPageTitle(position: Int): CharSequence? = mTitles[position]

    fun addFragment(title: String, fragment: Fragment) {
        mTitles.add(title)
        mFragments.add(fragment)
    }

}
