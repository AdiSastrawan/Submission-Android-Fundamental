package com.adisastrawan.mysearchsubmission.ui.detail.follower

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserFollowerEntity
import com.adisastrawan.mysearchsubmission.data.remote.respond.DetailUserResponse
import com.adisastrawan.mysearchsubmission.databinding.ItemRowUserBinding
import com.bumptech.glide.Glide

class FollowerAdapter : ListAdapter<UserFollowerEntity, FollowerAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserFollowerEntity>(){
            override fun areItemsTheSame(oldItem: UserFollowerEntity, newItem: UserFollowerEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserFollowerEntity, newItem: UserFollowerEntity): Boolean {
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
    class ViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user : UserFollowerEntity){
            binding.tvUsername.text = user.username
            Glide.with(itemView)
                .load(user.avatarUrl)
                .into(binding.ivUser)
        }
    }

}
