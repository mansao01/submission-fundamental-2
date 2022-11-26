package com.mansao.githubapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mansao.githubapp.data.remote.response.ResponseItem
import com.mansao.githubapp.data.remote.response.UserResponse
import com.mansao.githubapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _listUserGitHub = MutableLiveData<List<ResponseItem>>()
    val listUserGitHub: LiveData<List<ResponseItem>> = _listUserGitHub

    private var _searchUserGitHub = MutableLiveData<List<ResponseItem>>()
    val searchUserGitHub: LiveData<List<ResponseItem>> = _searchUserGitHub

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _showToast = MutableLiveData<Boolean>()
    val showToast: LiveData<Boolean> = _showToast

    fun getAllUser() {
        val client = ApiConfig.getService().getUsers()
        client.enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: Response<List<ResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _listUserGitHub.postValue(responseBody!!)
                }
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message}")
                _showToast.value = true
            }

        })
    }

    fun searchUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getService().searchUser(username)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if (responseBody != null) {
                    _searchUserGitHub.value = responseBody.item
                }
            }


            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message}")
                _showToast.value = true
            }

        })
    }


    companion object {
        private const val TAG = "MainViewModel"
    }
}