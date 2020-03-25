package com.github.lusingander.github.prs

import com.github.lusingander.github.GraphQLRequests

class SearchUserPullRequests(
    private val id: String
): GraphQLRequests() {

    private fun searchQuery(): String =
        """\"author:${this.id} -user:${this.id} is:pr sort:created-desc\""""

    override fun body() = """
        {
          search(query: ${this.searchQuery()}, type: ISSUE, first: 50, after: null) {
            issueCount
            edges {
              cursor
              node {
                ... on PullRequest {
                  title
                  state
                  number
                  url
                  additions
                  deletions
                  comments {
                    totalCount
                  }
                  reviews {
                    totalCount
                  }
                  createdAt
                  closedAt
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
                }
              }
            }
          }
        }
        """
}