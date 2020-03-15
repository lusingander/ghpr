package com.github.lusingander

import com.github.lusingander.github.GraphQLClient
import io.javalin.Javalin

fun main() {
    with (Javalin.create()) {
        get("/") { ctx ->
            ctx.result("NOT IMPLEMENTED")
        }
        get("/api/user/:id") { ctx ->
            val id = ctx.pathParam("id")
            val response = GraphQLClient(id).request()
            ctx.result(response)
        }
        start(Config.port())
    }
}