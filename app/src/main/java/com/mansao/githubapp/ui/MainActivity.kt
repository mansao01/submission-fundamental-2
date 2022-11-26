package com.mansao.githubapp.ui

import android.app.SearchManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.mansao.githubapp.R
import com.mansao.githubapp.adapter.ListUserAdapter
import com.mansao.githubapp.databinding.ActivityMainBinding
import com.mansao.githubapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var adapter: ListUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListUserAdapter()
        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
        }
        searchUser()
        showListUser()

    }
    private fun showListUser() {

        mainViewModel.getAllUser()
        mainViewModel.listUserGitHub.observe(this) {
            adapter.setListUser(it)
        }
        mainViewModel.isLoading.observe(this) {
            showProgressBar(it)
        }
        mainViewModel.showToast.observe(this) {
            showToast(it)
        }
    }
    private fun searchUser() {
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        binding.apply {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this@MainActivity.componentName))
            searchView.queryHint = getString(R.string.Search_user)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { mainViewModel.searchUser(it) }
                    mainViewModel.searchUserGitHub.observe(this@MainActivity) {
                        adapter.setListUser(it)
                    }
                    mainViewModel.isLoading.observe(this@MainActivity) {
                        showProgressBar(it)
                    }
                    mainViewModel.showToast.observe(this@MainActivity) {
                        showToast(it)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }


    private fun showProgressBar(state: Boolean) {
        binding.apply {

            if (state) progressBar.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun showToast(state: Boolean) {
        if (state) Toast.makeText(this, getString(R.string.something_wrong), Toast.LENGTH_SHORT)
            .show()
    }

}