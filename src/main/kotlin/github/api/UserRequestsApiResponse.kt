package com.github.lusingander.github.api

import com.github.lusingander.github.user.UserRequestsResponse

data class UserRequestsApiResponse(
    val login: String,
    val name: String,
    val organization: String,
    val location: String,
    val link: String,
    val avatar: String
) {
    companion object {
        fun build(src: UserRequestsResponse): UserRequestsApiResponse {
            val user = src.data.user
            return UserRequestsApiResponse(
                name = user.name ?: "",
                login = user.login,
                organization = user.company ?: "",
                location = user.location ?: "",
                link = user.websiteUrl ?: "",
                avatar = user.avatarUrl
            )
        }
    }
}
