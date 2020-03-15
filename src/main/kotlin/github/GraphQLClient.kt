package com.github.lusingander.github

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.result.Result
import com.github.lusingander.Config

class GraphQLClient(
    private val id: String
) {

    fun request(): String {
        return executePost(UserPullRequests.queryJson(this.id))
    }

    private fun executePost(query: String): String {
        val (_, _, result) = Fuel.post(Config.githubApiUrl())
            .header(Headers.AUTHORIZATION, "bearer ${Config.githubApiToken()}")
            .body(query)
            .responseString()

        if (result is Result.Failure) {
            throw result.getException()
        }
        return result.get()
    }
}