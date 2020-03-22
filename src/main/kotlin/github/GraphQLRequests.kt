package com.github.lusingander.github

abstract class GraphQLRequests {

    fun query(): String {
        val formattedQuery = body()
            .lines()
            .joinToString(separator = " ") { it.trim() }
        return """{"query": "$formattedQuery"}"""
    }

    protected abstract fun body(): String
}