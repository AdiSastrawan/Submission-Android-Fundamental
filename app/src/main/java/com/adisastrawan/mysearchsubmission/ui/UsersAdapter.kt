package com.adisastrawan.mysearchsubmission.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adisastrawan.mysearchsubmission.data.respond.ItemsItem
import com.adisastrawan.mysearchsubmission.databinding.ItemRowUserBinding
import com.bumptech.glide.Glide

class UsersAdapter : ListAdapter<ItemsItem, UsersAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>(){
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }
    class ViewHolder(val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user : ItemsItem){
            binding.tvUsername.text = user.login
            Glide.with(itemView)
                .load(user.avatarUrl)
                .into(binding.ivUser)
        }
    }

}
