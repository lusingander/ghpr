package com.github.lusingander.github.user

import com.github.lusingander.github.GraphQLRequests

class UserRequests(
    private val id: String
): GraphQLRequests() {

    override fun body() = """
        {
          user(login: \"${this.id}\") {
            login
            name
            location
            company
            websiteUrl
            avatarUrl
          }
        }
        """
}
