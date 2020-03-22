package com.github.lusingander.github.prs

import com.github.lusingander.github.api.UserPullRequestsResponse
import com.github.lusingander.github.api.buildUserResponseFrom
import com.github.lusingander.github.GraphQLClient

class UserPullRequestsClient(private val id: String): GraphQLClient() {
    fun request(): UserPullRequestsResponse {
        val query = UserPullRequests(this.id).query()
        val res = executePost<com.github.lusingander.github.prs.UserPullRequestsResponse>(query)
        return buildUserResponseFrom(res)
    }
}

