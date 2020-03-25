package com.github.lusingander.github.prs

import com.github.lusingander.github.api.UserPullRequestsApiResponse

data class SearchUserPullRequestsResponse(
    val data: Data
) {

    data class Data(
        val search: Search
    )

    data class Search(
        val issueCount: Int,
        val edges: List<Edge>
    )

    data class Edge(
        val cursor: String,
        val node: PullRequestNode
    )

    data class PullRequestNode(
        val repository: Repository,
        val title: String,
        val state: String,
        val number: Int,
        val url: String,
        val additions: Int,
        val deletions: Int,
        val comments: Comments,
        val reviews: Reviews,
        val createdAt: String,
        val closedAt: String?
    )

    data class Comments(
        val totalCount: Int
    )

    data class Reviews(
        val totalCount: Int
    )

    data class Repository(
        val name: String,
        val description: String?,
        val owner: Owner,
        val primaryLanguage: PrimaryLanguage?,
        val stargazers: Stargazers,
        val watchers: Watchers,
        val forkCount: Int
    )

    data class Owner(
        val login: String
    )

    data class PrimaryLanguage(
        val name: String,
        val color: String?
    )

    data class Stargazers(
        val totalCount: Int
    )

    data class Watchers(
        val totalCount: Int
    )

    fun buildApiResponse(): UserPullRequestsApiResponse {
        return UserPullRequestsApiResponse(
            owners = buildOwners(this.data.search.edges),
            cursors = buildCursors(this.data.search.edges),
            totalCount = this.data.search.issueCount
        )
    }

    companion object {
        private const val GITHUB_BASE_URL = "https://github.com/"

        private fun buildOwners(edges: List<Edge>): List<UserPullRequestsApiResponse.Owner> {
            val prs = edges.map { it.node }
            return prs.groupBy {
                it.repository.owner.login
            }.map {
                buildOwner(it.key, it.value)
            }
        }

        private fun buildOwner(name: String, prs: List<PullRequestNode>): UserPullRequestsApiResponse.Owner {
            val url = GITHUB_BASE_URL + name
            return UserPullRequestsApiResponse.Owner(
                name,
                url,
                buildRepositories(prs, url)
            )
        }

        private fun buildRepositories(prs: List<PullRequestNode>, ownerUrl: String): List<UserPullRequestsApiResponse.Repository> {
            return prs.groupBy {
                it.repository.name
            }.map {
                buildRepository(it.key, it.value, ownerUrl)
            }
        }

        private fun buildRepository(name: String, prs: List<PullRequestNode>, ownerUrl: String): UserPullRequestsApiResponse.Repository {
            val url = "$ownerUrl/$name"
            val repo = prs.first().repository
            return UserPullRequestsApiResponse.Repository(
                name = name,
                description = repo.description ?: "",
                url = url,
                watchers = repo.watchers.totalCount,
                stars = repo.stargazers.totalCount,
                forks = repo.forkCount,
                langName = repo.primaryLanguage?.name,
                langColor = repo.primaryLanguage?.color,
                pullRequests = prs.map { buildPullRequest(it) }
            )
        }

        private fun buildPullRequest(pr: PullRequestNode): UserPullRequestsApiResponse.PullRequest {
            return UserPullRequestsApiResponse.PullRequest(
                title = pr.title,
                state = pr.state,
                number = pr.number,
                url = pr.url,
                additions = pr.additions,
                deletions = pr.deletions,
                comments = pr.reviews.totalCount + pr.comments.totalCount,
                createdAt = pr.createdAt,
                closedAt = pr.closedAt
            )
        }

        private fun buildCursors(edges: List<Edge>): UserPullRequestsApiResponse.Cursors {
            return UserPullRequestsApiResponse.Cursors(
                first = edges.first().cursor,
                last = edges.last().cursor
            )
        }
    }
}