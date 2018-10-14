package com.sapuglha.coroutinesexploration.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sapuglha.coroutinesexploration.R
import com.sapuglha.coroutinesexploration.data.type.UserEntity
import com.sapuglha.coroutinesexploration.databinding.ItemUserBinding


class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private var items: List<UserEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LoadViewHolder(parent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is LoadViewHolder && items.size > position) {
            holder.bind(items[position])
        }
    }

    fun update(items: List<UserEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class LoadViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
    ) : ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            binding.user = user
        }
    }
}

