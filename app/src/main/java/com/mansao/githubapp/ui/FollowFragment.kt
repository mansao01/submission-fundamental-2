package com.mansao.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mansao.githubapp.adapter.ListUserAdapter
import com.mansao.githubapp.databinding.FragmentFollowBinding
import com.mansao.githubapp.viewmodel.FollowViewModel

class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    private val viewModel by viewModels<FollowViewModel>()
    private lateinit var adapter: ListUserAdapter


    private var tabName: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabName = arguments?.getString(ARG_TAB)
        adapter = ListUserAdapter()
        binding.apply {
            rvFollow.adapter = adapter
            rvFollow.setHasFixedSize(true)
            rvFollow.layoutManager = LinearLayoutManager(view.context)
        }

        if (tabName == TAB_FOLLOWING) {
            getFollowing()
        } else if (tabName == TAB_FOLLOWERS) {
            getFollowers()
        }
    }

    private fun getFollowing() {
        val username = arguments?.getString(DetailActivity.EXTRA_DATA)

        viewModel.apply {
            username?.let { getFollowing(it) }
            listFollowing.observe(viewLifecycleOwner) {
                adapter.setListUser(it)
            }
            isLoading.observe(viewLifecycleOwner) {
                showProgressBar(it)
            }
        }
    }

    private fun getFollowers() {
        val username = arguments?.getString(DetailActivity.EXTRA_DATA)

        username?.let { viewModel.getFollowers(it) }
        viewModel.apply {
            listFollowers.observe(viewLifecycleOwner) {
                adapter.setListUser(it)
            }
            isLoading.observe(viewLifecycleOwner) {
                showProgressBar(it)
            }
        }
    }

    private fun showProgressBar(state: Boolean) {
        if (state) binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    companion object {
        const val ARG_TAB = "tab_name"
        const val TAB_FOLLOWING = "following"
        const val TAB_FOLLOWERS = "bookmark"
    }
}