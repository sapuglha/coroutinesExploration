package com.sapuglha.coroutinesexploration.presentation.user.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sapuglha.coroutinesexploration.domain.type.User

@BindingAdapter("usersList")
fun RecyclerView.bindItems(users: List<User>?) {
    if (users == null) return

    val adapter = adapter as ListRowAdapter
    adapter.update(users)
}
