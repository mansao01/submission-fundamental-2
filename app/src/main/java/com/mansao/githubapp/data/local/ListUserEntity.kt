package com.mansao.githubapp.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListUserEntity(
    var id: Int,
    var login: String,
    var avatar: String
): Parcelable