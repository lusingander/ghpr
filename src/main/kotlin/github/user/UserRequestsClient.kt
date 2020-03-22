package com.github.lusingander.github.user

import com.github.lusingander.github.api.UserRequestsApiResponse
import com.github.lusingander.github.GraphQLClient

class UserRequestsClient(private val id: String): GraphQLClient() {
    fun request(): UserRequestsApiResponse {
        val query = UserRequests(this.id).query()
        val res = executePost<UserRequestsResponse>(query)
        val user = res.data.user
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