package com.github.lusingander.github.prs

import com.github.lusingander.github.api.UserPullRequestsApiResponse
import com.github.lusingander.github.GraphQLClient

class UserPullRequestsClient(private val id: String): GraphQLClient() {
    fun request(): UserPullRequestsApiResponse {
        val query = UserPullRequests(this.id).query()
        val res = executePost<UserPullRequestsResponse>(query)
        return UserPullRequestsApiResponse.build(res)
    }
}

