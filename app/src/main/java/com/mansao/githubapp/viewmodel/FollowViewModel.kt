package com.mansao.githubapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mansao.githubapp.data.remote.response.ResponseItem
import com.mansao.githubapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel : ViewModel() {
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listFollowing = MutableLiveData<List<ResponseItem>>()
    val listFollowing: LiveData<List<ResponseItem>> = _listFollowing

    private val _listFollowers = MutableLiveData<List<ResponseItem>>()
    val listFollowers: LiveData<List<ResponseItem>> = _listFollowers

    fun getFollowing(username: String) {
        val client = ApiConfig.getService().getFollowing(username)
        client.enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: Response<List<ResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _listFollowing.postValue(responseBody!!)
                }
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getFollowers(username: String) {
        val client = ApiConfig.getService().getFollowers(username)
        client.enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: Response<List<ResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _listFollowers.postValue(responseBody!!)
                }
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        private const val TAG = "FollowViewModel"
    }
}