package com.github.lusingander.github.api

import com.github.lusingander.github.prs.UserPullRequestsResponse

data class UserPullRequestsApiResponse(
    val owners: List<Owner>,
    val cursors: Cursors,
    val totalCount: Int
) {
    companion object {
        private const val GITHUB_BASE_URL = "https://github.com/"

        fun build(src: UserPullRequestsResponse): UserPullRequestsApiResponse {
            val prs = src.data.user.pullRequests
            return UserPullRequestsApiResponse(
                owners = buildOwners(prs.nodes),
                cursors = buildCursors(prs.edges),
                totalCount = prs.totalCount
            )
        }

        private fun buildCursors(edges: List<UserPullRequestsResponse.Edge>): Cursors {
            return Cursors(edges.first().cursor, edges.last().cursor)
        }

        private fun buildOwners(nodes: List<UserPullRequestsResponse.PullRequestNode>): List<Owner> {
            return nodes.groupBy {
                it.repository.owner.login
            }.map {
                buildOwner(it.key, it.value)
            }
        }

        private fun buildOwner(name: String, prs: List<UserPullRequestsResponse.PullRequestNode>): Owner {
            val url = GITHUB_BASE_URL + name
            return Owner(
                name,
                url,
                buildRepositories(prs, url)
            )
        }

        private fun buildRepositories(prs: List<UserPullRequestsResponse.PullRequestNode>, ownerUrl: String): List<Repository> {
            return prs.groupBy {
                it.repository.name
            }.map {
                buildRepository(it.key, it.value, ownerUrl)
            }
        }

        private fun buildRepository(name: String, prs: List<UserPullRequestsResponse.PullRequestNode>, ownerUrl: String): Repository {
            val url = "$ownerUrl/$name"
            val repo = prs.first().repository
            return Repository(
                name = name,
                description = repo.description,
                url = url,
                watchers = repo.watchers.totalCount,
                stars = repo.stargazers.totalCount,
                forks = repo.forkCount,
                langName = repo.primaryLanguage.name,
                langColor = repo.primaryLanguage.color,
                pullRequests = prs.map { buildPullRequest(it) }
            )
        }

        private fun buildPullRequest(pr: UserPullRequestsResponse.PullRequestNode): PullRequest {
            return PullRequest(
                title = pr.title,
                state = pr.state,
                number = pr.number,
                url = pr.url,
                additions = pr.additions,
                deletions = pr.deletions,
                createdAt = pr.createdAt,
                closedAt = pr.closedAt
            )
        }
    }

    data class Owner(
        val name: String,
        val url: String,
        val repositories: List<Repository>
    )

    data class Repository(
        val name: String,
        val description: String,
        val url: String,
        val watchers: Int,
        val stars: Int,
        val forks: Int,
        val langName: String,
        val langColor: String,
        val pullRequests: List<PullRequest>
    )

    data class PullRequest(
        val title: String,
        val state: String,
        val number: Int,
        val url: String,
        val additions: Int,
        val deletions: Int,
        val createdAt: String,
        val closedAt: String?
    )

    data class Cursors(
        val first: String,
        val last: String
    )
}

