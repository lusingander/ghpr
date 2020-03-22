package com.github.lusingander.github

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.jackson.responseObject
import com.github.lusingander.Config
import java.lang.Exception

abstract class GraphQLClient {

    protected inline fun <reified T : Any> executePost(query: String): T {
        try {
            val (_, _, result) = Fuel.post(Config.githubApiUrl())
                .header(Headers.AUTHORIZATION, "bearer ${Config.githubApiToken()}")
                .body(query)
                .responseObject<T>()
            return result.get()
        } catch (e: Exception) {
            // TODO: should have an appropriate error definition
            throw GraphQLRequestException(e)
        }
    }
}