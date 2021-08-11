package com.sumeet.algovisualizer.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sumeet.algovisualizer.ui.bottomfragments.CodeViewFragment
import com.sumeet.algovisualizer.ui.bottomfragments.LogsFragment

class BottomViewPagerAdapter(fragment : Fragment)
    : FragmentStateAdapter(fragment)
{
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LogsFragment.newInstance()
            1 -> CodeViewFragment.newInstance()
            else -> LogsFragment.newInstance()
        }
    }
}