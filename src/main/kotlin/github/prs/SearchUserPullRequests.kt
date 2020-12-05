package com.github.lusingander.github.prs

import com.github.lusingander.github.GraphQLRequests
import com.github.lusingander.kraphql.github.SearchType
import com.github.lusingander.kraphql.github.query

class SearchUserPullRequests(
    private val id: String
) : GraphQLRequests() {

    override fun body(): String {
        val q = query {
            search(
                query = "author:$id -user:$id is:pr sort:created-desc",
                type = SearchType.ISSUE,
                first = 50,
                after = null
            ) {
                issueCount
                edges {
                    cursor
                    node {
                        `on PullRequest` {
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
        return q.toEscapedString()
    }
}