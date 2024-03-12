package com.adisastrawan.mysearchsubmission.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adisastrawan.mysearchsubmission.data.local.database.enitity.UserDetailEntity
import com.adisastrawan.mysearchsubmission.databinding.ItemRowUserBinding
import com.bumptech.glide.Glide

class FavoriteAdapter : ListAdapter<UserDetailEntity, FavoriteAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserDetailEntity>(){
            override fun areItemsTheSame(oldItem: UserDetailEntity, newItem: UserDetailEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserDetailEntity, newItem: UserDetailEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
    class ViewHolder(private val binding:ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user : UserDetailEntity){
            binding.tvUsername.text = user.username
            Glide.with(itemView)
                .load(user.avatarUrl)
                .into(binding.ivUser)
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
            val toDetailFragment = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment()
            toDetailFragment.username = user.username
            it.findNavController().navigate(toDetailFragment)
        }
    }
}