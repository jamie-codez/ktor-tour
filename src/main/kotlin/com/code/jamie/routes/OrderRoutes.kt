package com.code.jamie.routes

import com.code.jamie.models.orderStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.listOrderRoute() {
    get("/order") {
        if (orderStorage.isEmpty()) {
            call.respond(status = HttpStatusCode.NotFound, message = "No orders found")
            return@get
        }
        call.respond(status = HttpStatusCode.OK, message = orderStorage)
    }
}

fun Route.getOrderRoute() {
    get("/order/{id}") {
        val id = call.parameters["id"] ?: return@get call.respond(
            status = HttpStatusCode.BadRequest,
            message = "id cannot be null"
        )
        val order = orderStorage.find { it.number == id } ?: return@get call.respond(
            status = HttpStatusCode.NotFound,
            message = "Not found"
        )
        call.respond(status = HttpStatusCode.OK, order)
    }
}

fun Route.totalizeOrderRoute() {
    get("/order/{id?}/total") {
        val id = call.parameters["id"] ?: return@get call.respond(
            status = HttpStatusCode.BadRequest,
            message = "id cannot be null"
        )
        val order =
            orderStorage.find { it.number == id } ?: return@get call.respond(
                status = HttpStatusCode.NotFound,
                message = "Not found"
            )
        val total = order.content.sumOf { it.price * it.amount }
        call.respond(status = HttpStatusCode.OK,total)
    }
}