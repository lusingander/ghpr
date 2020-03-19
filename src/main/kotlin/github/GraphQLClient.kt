package com.github.lusingander.github

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.result.failure
import com.github.lusingander.Config
import com.github.lusingander.UserResponse
import com.github.lusingander.buildUserResponseFrom

class GraphQLClient(
    private val id: String
) {

    fun request(): UserResponse {
        return executePost(UserPullRequests.queryJson(this.id))
    }

    private fun executePost(query: String): UserResponse {
        val (_, _, result) = Fuel.post(Config.githubApiUrl())
            .header(Headers.AUTHORIZATION, "bearer ${Config.githubApiToken()}")
            .body(query)
            .responseObject<UserPullRequestsResponse>()

        result.failure {
            throw it
        }
        return buildUserResponseFrom(result.get())
    }
}