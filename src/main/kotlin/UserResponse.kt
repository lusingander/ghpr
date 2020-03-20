package com.github.lusingander

import com.github.lusingander.github.Edge
import com.github.lusingander.github.PullRequestNode
import com.github.lusingander.github.UserPullRequestsResponse

private const val GITHUB_BASE_URL = "https://github.com/"

fun buildUserResponseFrom(src: UserPullRequestsResponse): UserResponse {
    val prs = src.data.user.pullRequests
    return UserResponse(
        owners = buildOwners(prs.nodes),
        cursors = buildCursors(prs.edges),
        totalCount = prs.totalCount
    )
}

private fun buildCursors(edges: List<Edge>): Cursors {
    return Cursors(edges.first().cursor, edges.last().cursor)
}

private fun buildOwners(nodes: List<PullRequestNode>): List<Owner> {
    return nodes.groupBy {
        it.repository.owner.login
    }.map {
        buildOwner(it.key, it.value)
    }
}

private fun buildOwner(name: String, prs: List<PullRequestNode>): Owner {
    val url = GITHUB_BASE_URL + name
    return Owner(name, url, buildRepositories(prs, url))
}

private fun buildRepositories(prs: List<PullRequestNode>, ownerUrl: String): List<Repository> {
    return prs.groupBy {
        it.repository.name
    }.map {
        buildRepository(it.key, it.value, ownerUrl)
    }
}

private fun buildRepository(name: String, prs: List<PullRequestNode>, ownerUrl: String): Repository {
    val url = "$ownerUrl/$name"
    val repo = prs.first().repository
    return Repository(
        name = name,
        url = url,
        stars = repo.stargazers.totalCount,
        forks = repo.forkCount,
        langName = repo.primaryLanguage.name,
        langColor = repo.primaryLanguage.color,
        pullRequests = prs.map { buildPullRequest(it) }
    )
}

private fun buildPullRequest(pr: PullRequestNode): PullRequest {
    return PullRequest(pr.title, pr.state, pr.url, pr.createdAt)
}

data class UserResponse(
    val owners: List<Owner>,
    val cursors: Cursors,
    val totalCount: Int
)

data class Owner(
    val name: String,
    val url: String,
    val repositories: List<Repository>
)

data class Repository(
    val name: String,
    val url: String,
    val stars: Int,
    val forks: Int,
    val langName: String,
    val langColor: String,
    val pullRequests: List<PullRequest>
)

data class PullRequest(
    val title: String,
    val state: String,
    val url: String,
    val createdAt: String
)

data class Cursors(
    val first: String,
    val last: String
)