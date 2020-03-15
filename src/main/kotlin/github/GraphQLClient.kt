package com.github.lusingander.github

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.result.Result
import com.github.lusingander.Config
import me.lazmaid.kraph.Kraph

class GraphQLClient(
    private val id: String
) {

    companion object {
        private const val GITHUB_API_URL = "https://api.github.com/graphql"
    }

    private val query = Kraph {
        query {
            fieldObject("user", args = mapOf("login" to id)) {
                field("company")
                fieldObject("pullRequests", args = mapOf("first" to 50)) {
                    fieldObject("nodes") {
                        fieldObject("repository") {
                            field("name")
                        }
                        field("title")
                        field("state")
                        field("url")
                    }
                }
            }
        }
    }

    fun request(): String {
        return executePost(query.toRequestString())
    }

    private fun executePost(query: String): String {
        val (_, _, result) = Fuel.post(GITHUB_API_URL)
            .header(Headers.AUTHORIZATION, "bearer ${Config.gitHubApiToken()}")
            .body(query)
            .responseString()

        if (result is Result.Failure) {
            throw result.getException()
        }
        return result.get()
    }
}