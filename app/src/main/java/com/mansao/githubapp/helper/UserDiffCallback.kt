package com.mansao.githubapp.helper

import androidx.recyclerview.widget.DiffUtil
import com.mansao.githubapp.data.remote.response.ResponseItem

class UserDiffCallback(
    private val mOldUserList: List<ResponseItem>,
//    private val mOldUserList: List<ListUserEntity>,
    private val mNewUserList: List<ResponseItem>
//    private val mNewUserList: List<ListUserEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize() = mOldUserList.size

    override fun getNewListSize() = mNewUserList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUserList[oldItemPosition].id == mNewUserList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldUserList[oldItemPosition]
        val newEmployee = mNewUserList[newItemPosition]
        return oldEmployee.login == newEmployee.login && oldEmployee.avatarUrl == newEmployee.avatarUrl
    }

}