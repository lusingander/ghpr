package com.github.lusingander.github.user

import com.github.lusingander.github.api.UserRequestsApiResponse
import com.github.lusingander.github.GraphQLClient

class UserRequestsClient(private val id: String) {
    fun request(): UserRequestsApiResponse {
        val client = GraphQLClient()
        val query = UserRequests(this.id).query()
        val res = client.executePost<UserRequestsResponse>(query)
        return UserRequestsApiResponse.build(res)
    }
}