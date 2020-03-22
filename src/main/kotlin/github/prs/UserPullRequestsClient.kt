package com.github.lusingander.github.prs

import com.github.lusingander.github.api.UserPullRequestsApiResponse
import com.github.lusingander.github.GraphQLClient

class UserPullRequestsClient(private val id: String) {
    fun request(): UserPullRequestsApiResponse {
        val client = GraphQLClient()
        val query = UserPullRequests(this.id).query()
        val res = client.executePost<UserPullRequestsResponse>(query)
        return UserPullRequestsApiResponse.build(res)
    }
}

