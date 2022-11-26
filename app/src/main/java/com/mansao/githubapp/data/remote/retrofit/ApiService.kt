package com.mansao.githubapp.data.remote.retrofit

import com.mansao.githubapp.data.remote.response.ResponseItem
import com.mansao.githubapp.data.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<ResponseItem>>

    @GET("search/users?")
    fun searchUser(
        @Query("q") q: String
    ): Call<UserResponse>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<ResponseItem>>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<ResponseItem>>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<ResponseItem>


}