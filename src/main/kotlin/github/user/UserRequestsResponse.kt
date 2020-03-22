package com.github.lusingander.github.user

data class UserRequestsResponse(
    val data: Data
) {

    data class Data(
        val user: User
    )

    data class User(
        val login: String,
        val name: String?,
        val company: String?,
        val location: String?,
        val websiteUrl: String?,
        val avatarUrl: String
    )
}