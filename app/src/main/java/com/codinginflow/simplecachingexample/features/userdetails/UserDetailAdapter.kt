package com.codinginflow.simplecachingexample.features.userdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.simplecachingexample.data.UserDetail
import com.codinginflow.simplecachingexample.databinding.UserDetailItemBinding

class UserDetailAdapter : ListAdapter<UserDetail, UserDetailAdapter.UserDetailViewHolder>(UserDetailComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailViewHolder {
        val binding =
            UserDetailItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserDetailViewHolder, position: Int) {
       val currentItem = getItem(position)
        if (currentItem != null){
            holder.bind(currentItem)
        }
    }

    class UserDetailViewHolder(private val binding: UserDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userDetail: UserDetail) {
            binding.apply {
                textViewId.text = userDetail.id
                textViewCategoryName.text = userDetail.category_name
                textViewSlug.text = userDetail.slug
            }
        }

    }
    class UserDetailComparator : DiffUtil.ItemCallback<UserDetail>(){
        override fun areItemsTheSame(oldItem: UserDetail, newItem: UserDetail) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserDetail, newItem: UserDetail)=
            oldItem == newItem


    }
}