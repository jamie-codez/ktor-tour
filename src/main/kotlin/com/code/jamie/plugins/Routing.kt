package com.code.jamie.plugins

import com.code.jamie.routes.customerRouting
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.util.Identity.encode
import kotlinx.serialization.json.JsonObject

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(status = HttpStatusCode.OK,message = "Server is up")
        }
        customerRouting()
    }
}
