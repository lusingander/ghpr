package com.github.lusingander

import com.github.lusingander.github.GraphQLClient
import io.javalin.Javalin
import io.javalin.plugin.rendering.vue.VueComponent

fun main() {
    with (Javalin.create()) {
        get("/") { ctx -> ctx.result("NOT IMPLEMENTED")}
        get("/user/:id", VueComponent("<user-prs></user-prs>"))
        get("/api/user/:id") { ctx ->
            val id = ctx.pathParam("id")
            val response = GraphQLClient(id).request()
            ctx.json(response)
        }
        start(Config.port())
    }
}