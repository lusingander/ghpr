package com.github.lusingander.github

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.jackson.responseObject
import com.github.lusingander.Config
import com.github.lusingander.UserPullRequestsResponse
import com.github.lusingander.buildUserResponseFrom
import java.lang.Exception

class GraphQLClient(
    private val id: String
) {

    fun request(): UserPullRequestsResponse {
        return executePost(UserPullRequests.queryJson(this.id))
    }

    private fun executePost(query: String): UserPullRequestsResponse {
        try {
            val (_, _, result) = Fuel.post(Config.githubApiUrl())
                .header(Headers.AUTHORIZATION, "bearer ${Config.githubApiToken()}")
                .body(query)
                .responseObject<GitHubUserPullRequestsResponse>()
            return buildUserResponseFrom(result.get())
        } catch (e: Exception) {
            // TODO: should have an appropriate error definition
            throw GraphQLRequestException(e)
        }
    }
}