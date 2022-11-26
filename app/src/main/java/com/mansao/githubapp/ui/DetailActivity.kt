package com.mansao.githubapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mansao.githubapp.R
import com.mansao.githubapp.adapter.SectionPagerAdapter
import com.mansao.githubapp.databinding.ActivityDetailBinding
import com.mansao.githubapp.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private val detailViewModel by viewModels<DetailViewModel>()
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        val receiveUsernameIntent = intent.getStringExtra(EXTRA_DATA)
        val bundle = Bundle()
        bundle.putString(EXTRA_DATA, receiveUsernameIntent)
        receiveUsernameIntent?.let { detailViewModel.getDetailUser(it) }

        detailViewModel.apply {
            detailUser.observe(this@DetailActivity) {
                binding.apply {
                    tvUsername.text = it.login
                    tvName.text = it.name
                    tvLocation.text = it.location
                    tvCompany.text = it.company
                    tvFollowing.text = it.following.toString()
                    tvFollower.text = it.followers.toString()
                    tvRepository.text = it.publicRepos.toString()

                    Glide.with(this@DetailActivity)
                        .load(it.avatarUrl)
                        .centerCrop()
                        .into(imgPhoto)
                }
            }
            showToast.observe(this@DetailActivity) {
                showToast(it)
            }
            isLoading.observe(this@DetailActivity) {
                showProgressBar(it)
            }
        }

        val sectionPagerAdapter = SectionPagerAdapter(this)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(
            binding.tabs, binding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun showProgressBar(state: Boolean) {
        binding.apply {
            if (state) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun showToast(state: Boolean) {
        if (state) Toast.makeText(this, getString(R.string.something_wrong), Toast.LENGTH_SHORT)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_DATA = "extra_data"

        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.following, R.string.followers)
    }
}