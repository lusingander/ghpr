package com.github.lusingander.github.api

data class UserRequestsApiResponse(
    val login: String,
    val name: String,
    val organization: String,
    val location: String,
    val link: String,
    val avatar: String
)
