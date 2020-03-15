package com.github.lusingander

object Config {
    fun githubApiUrl(): String = "https://api.github.com/graphql"
    fun port(): Int = 7000
    fun githubApiToken(): String? = ProcessBuilder().environment()["GITHUB_API_TOKEN"]
}