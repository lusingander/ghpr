package com.github.lusingander.github.prs

import com.github.lusingander.github.GraphQLClient
import com.github.lusingander.github.api.UserPullRequestsApiResponse

class SearchUserPullRequestsClient(private val id: String) {
    fun request(): UserPullRequestsApiResponse {
        val client = GraphQLClient()
        val query = SearchUserPullRequests(this.id).query()
        val res = client.executePost<SearchUserPullRequestsResponse>(query)
        return res.buildApiResponse()
    }
}