package com.sapuglha.coroutinesexploration.domain.type

data class User(
    val id: String? = null,
    val username: String,
    val firstname: String,
    val lastname: String
) {
    val fullname: String
        get() = "$firstname $lastname"
}
