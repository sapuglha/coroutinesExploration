package com.sapuglha.coroutinesexploration.presentation.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sapuglha.coroutinesexploration.data.type.UserEntity

@BindingAdapter("usersList")
fun RecyclerView.bindItems(users: List<UserEntity>?) {
    if (users == null) return

    val adapter = adapter as UsersAdapter
    adapter.update(users)
}
