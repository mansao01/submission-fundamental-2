package com.mansao.githubapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mansao.githubapp.R
import com.mansao.githubapp.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFollowing()
    }

    private fun getFollowing(){
        val username = arguments?.getString(DetailActivity.EXTRA_DATA)
        binding.tvTest.text = username
    }

    companion object {
        const val ARG_TAB = "tab_name"
        const val TAB_FOLLOWING = "following"
        const val TAB_FOLLOWERS = "bookmark"
        private var TAG = FollowFragment::class.java.simpleName
    }
}