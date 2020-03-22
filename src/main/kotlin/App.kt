package com.github.lusingander

import com.github.lusingander.github.GraphQLRequestException
import com.github.lusingander.github.prs.UserPullRequestsClient
import io.javalin.Javalin
import io.javalin.plugin.rendering.vue.VueComponent
import org.slf4j.LoggerFactory

fun main() {
    val log = LoggerFactory.getLogger("app.AppKt")

    with (Javalin.create()) {
        with (config) {
            requestLogger { ctx, ms ->
                log.info("${ctx.status()} url=${ctx.fullUrl()} time=$ms UA=${ctx.userAgent()} ")
            }
            addStaticFiles("/public")
        }
        get("/", VueComponent("<user-search></user-search>"))
        get("/user/:id", VueComponent("<user-prs></user-prs>"))
        get("/api/user/:id") { ctx ->
            val id = ctx.pathParam("id")
            val response = UserPullRequestsClient(id).request()
            ctx.json(response)
        }
        exception(GraphQLRequestException::class.java) { _, ctx -> ctx.status(400) }
        start(Config.port())
    }
}