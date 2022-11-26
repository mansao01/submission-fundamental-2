package com.mansao.githubapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mansao.githubapp.data.remote.response.ResponseItem
import com.mansao.githubapp.databinding.ItemRowUserBinding
import com.mansao.githubapp.helper.UserDiffCallback
import com.mansao.githubapp.ui.DetailActivity

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.ListUserViewHolder>() {
    private val listUser = ArrayList<ResponseItem>()

    fun setListUser(listUser: List<ResponseItem>) {
        val diffCallback = UserDiffCallback(this.listUser, listUser)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listUser.clear()
        this.listUser.addAll(listUser)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(listUser[position])


    }

    override fun getItemCount() = listUser.size

    inner class ListUserViewHolder(private val binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listUser: ResponseItem) {
            binding.apply {
                tvItemName.text = listUser.login
                Glide.with(itemView.context)
                    .load(listUser.avatarUrl)
                    .centerCrop()
                    .into(imgPhoto)

                cardView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                        .putExtra(DetailActivity.EXTRA_DATA, listUser.login)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}