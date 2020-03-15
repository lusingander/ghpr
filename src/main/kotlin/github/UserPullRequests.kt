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
                  owner {
                    login
                  }
                }
                title
                state
                url
                createdAt
              }
              edges {
                cursor
                node {
                  repository {
                    name
                  }
                  createdAt
                }
              }
              totalCount
            }
          }
        }
        """
    }
}

