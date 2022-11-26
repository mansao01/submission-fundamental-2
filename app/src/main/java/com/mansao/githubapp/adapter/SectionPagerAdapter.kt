package com.mansao.githubapp.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mansao.githubapp.ui.FollowFragment

class SectionPagerAdapter internal constructor(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
        val bundle = Bundle()
        if (position == 0){
            bundle.putString(FollowFragment.ARG_TAB, FollowFragment.TAB_FOLLOWERS)
        }else{
            bundle.putString(FollowFragment.ARG_TAB, FollowFragment.TAB_FOLLOWING)
        }
        fragment.arguments = bundle
        return fragment
    }


    override fun getItemCount() = 2

}