package com.github.lusingander.github.user

import com.github.lusingander.github.GraphQLRequests
import com.github.lusingander.kraphql.github.query

class UserRequests(
    private val id: String
) : GraphQLRequests() {

    override fun body(): String {
        val q = query {
            user(login = id) {
                login
                name
                location
                company
                websiteUrl
                avatarUrl
            }
        }
        return q.toEscapedString()
    }
}
