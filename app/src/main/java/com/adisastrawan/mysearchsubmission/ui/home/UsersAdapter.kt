package com.adisastrawan.mysearchsubmission.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserEntity
import com.adisastrawan.mysearchsubmission.data.remote.respond.ItemsItem
import com.adisastrawan.mysearchsubmission.databinding.ItemRowUserBinding
import com.bumptech.glide.Glide

class UsersAdapter : ListAdapter<UserEntity, UsersAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object  {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserEntity>(){
            override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
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
        holder.itemView.setOnClickListener{
            val toDetailFragment = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
            toDetailFragment.username = user.username
            it.findNavController().navigate(toDetailFragment)
        }
    }
    class ViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user : UserEntity){
            binding.tvUsername.text = user.username
            Glide.with(itemView)
                .load(user.avatarUrl)
                .into(binding.ivUser)
        }
    }

}
