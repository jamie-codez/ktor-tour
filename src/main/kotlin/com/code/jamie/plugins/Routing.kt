package com.code.jamie.plugins

import com.code.jamie.routes.customerRouting
import com.code.jamie.routes.getOrderRoute
import com.code.jamie.routes.listOrderRoute
import com.code.jamie.routes.totalizeOrderRoute
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.util.Identity.encode
import kotlinx.serialization.json.JsonObject

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Server is up")
        }
        customerRouting()
        listOrderRoute()
        getOrderRoute()
        totalizeOrderRoute()
    }
}
