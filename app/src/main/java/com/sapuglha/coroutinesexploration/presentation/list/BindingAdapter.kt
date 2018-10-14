package com.sapuglha.coroutinesexploration.presentation.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sapuglha.coroutinesexploration.data.type.UserEntity

@BindingAdapter("usersList")
fun RecyclerView.bindItems(users: List<UserEntity>?) {
    if (users == null) return

    val adapter = adapter as ListRowAdapter
    adapter.update(users)
}
