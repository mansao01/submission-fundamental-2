package com.mansao.githubapp.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mansao.githubapp.ui.FollowFragment

class SectionPagerAdapter internal constructor(activity: AppCompatActivity, data: Bundle) :
    FragmentStateAdapter(activity) {
    private var fragmentBundle: Bundle

    init {
        fragmentBundle = data
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
        if (position == 0) {
            fragmentBundle.putString(FollowFragment.ARG_TAB, FollowFragment.TAB_FOLLOWERS)
        } else {
            fragmentBundle.putString(FollowFragment.ARG_TAB, FollowFragment.TAB_FOLLOWING)
        }
        fragment.arguments = this.fragmentBundle
        return fragment
    }


    override fun getItemCount() = 2

}