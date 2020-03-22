package com.github.lusingander.github.prs

data class UserPullRequestsResponse(
    val data: Data
) {

    data class Data(
        val user: User
    )

    data class User(
        val pullRequests: PullRequest
    )

    data class PullRequest(
        val nodes: List<PullRequestNode>,
        val edges: List<Edge>,
        val totalCount: Int
    )

    data class PullRequestNode(
        val repository: Repository,
        val title: String,
        val state: String,
        val number: Int,
        val url: String,
        val additions: Int,
        val deletions: Int,
        val createdAt: String,
        val closedAt: String?
    )

    data class Edge(
        val cursor: String
    )

    data class Repository(
        val name: String,
        val description: String,
        val owner: Owner,
        val primaryLanguage: PrimaryLanguage,
        val stargazers: Stargazers,
        val watchers: Watchers,
        val forkCount: Int
    )

    data class Owner(
        val login: String
    )

    data class PrimaryLanguage(
        val name: String,
        val color: String
    )

    data class Stargazers(
        val totalCount: Int
    )

    data class Watchers(
        val totalCount: Int
    )
}
