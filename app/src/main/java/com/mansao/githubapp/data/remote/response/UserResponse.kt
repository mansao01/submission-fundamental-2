package com.mansao.githubapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("items")
    val item: List<ResponseItem>
)

data class ResponseItem(

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("following")
    val following: Int,

    @field:SerializedName("followers")
    val followers: Int,

    @field:SerializedName("email")
    val email: Any,

    @field:SerializedName("company")
    val company: String,

    @field:SerializedName("public_repos")
    val publicRepos: Int,
)
