package com.github.lusingander.github.prs

import com.github.lusingander.github.GraphQLRequests

class UserPullRequests(
    private val id: String
): GraphQLRequests() {

    override fun body() = """
        {
          user(login: \"${this.id}\") {
            pullRequests(first: 50, orderBy: {field: CREATED_AT, direction: DESC}) {
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
                  watchers {
                    totalCount
                  }
                  forkCount
                }
                title
                state
                number
                url
                additions
                deletions
                createdAt
                closedAt
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
