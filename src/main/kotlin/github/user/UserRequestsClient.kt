package com.github.lusingander.github.user

import com.github.lusingander.github.api.UserRequestsResponse
import com.github.lusingander.github.GraphQLClient

class UserRequestsClient(private val id: String): GraphQLClient() {
    fun request(): UserRequestsResponse {
        val query = UserRequests(this.id).query()
        val res = executePost<com.github.lusingander.github.user.UserRequestsResponse>(query)
        val user = res.data.user
        return UserRequestsResponse(
            name = user.name ?: "",
            login = user.login,
            organization = user.company ?: "",
            location = user.location ?: "",
            link = user.websiteUrl ?: "",
            avatar = user.avatarUrl
        )
    }
}