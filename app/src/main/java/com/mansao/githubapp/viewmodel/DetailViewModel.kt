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

class DetailViewModel : ViewModel() {
    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _showToast = MutableLiveData<Boolean>()
    val showToast: LiveData<Boolean> = _showToast

    private var _detailUser = MutableLiveData<ResponseItem>()
    val detailUser: LiveData<ResponseItem> = _detailUser


    fun getDetailUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getService().getDetailUser(username)
        client.enqueue(object : Callback<ResponseItem>{
            override fun onResponse(call: Call<ResponseItem>, response: Response<ResponseItem>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val responseBody = response.body()
                    _detailUser.postValue(responseBody!!)
                }
            }

            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message}")
                _showToast.value = true
            }

        })

    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}