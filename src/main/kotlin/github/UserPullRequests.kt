package com.github.lusingander.github

class UserPullRequests {

    companion object {

        fun queryJson(id: String, size: Int = 50): String {
            val formattedQuery = query(id, size)
                .lines()
                .joinToString(separator = " ") { it.trim() }
            return """{"query": "$formattedQuery"}"""
        }

        private fun query(id: String, size: Int) = """
        {
          user(login: \"$id\") {
            pullRequests(first: $size, orderBy: {field: CREATED_AT, direction: DESC}) {
              nodes {
                repository {
                  name
                  description
                  owner {
                    login
                  }
                  primaryLanguage {
                    name
                    color
                  }
                  stargazers {
                    totalCount
                  }
                  forkCount
                }
                title
                state
                url
                createdAt
              }
              edges {
                cursor
              }
              totalCount
            }
          }
        }
        """
    }
}

data class UserPullRequestsResponse(
    val data: UserPullRequestsData
)

data class UserPullRequestsData(
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
    val url: String,
    val createdAt: String
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
