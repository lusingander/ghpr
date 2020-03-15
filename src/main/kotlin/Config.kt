package com.github.lusingander

object Config {
    fun port(): Int = 7000
    fun gitHubApiToken(): String? = ProcessBuilder().environment()["GITHUB_API_TOKEN"]
}