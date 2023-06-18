package com.code.jamie.routes

import com.code.jamie.models.Customer
import com.code.jamie.models.customerStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting() {
    route("/customer") {
        get {
            if (customerStorage.isEmpty()) {
                call.respondText("No customers found")
                return@get
            }
            call.respond(customerStorage)
        }
        get("{id?}") {
            val id =
                call.parameters["id"] ?: return@get call.respondText("Missing id", status = HttpStatusCode.BadRequest)
            val customer = customerStorage.find { it.id == id } ?: return@get call.respondText(
                "No Customer with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(customer)
        }
        post {
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
            call.respondText("Customer added successfully", status = HttpStatusCode.Created)
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadGateway)
            if (customerStorage.removeIf { it.id == id }){
                call.respondText("Customer removed successfully", status = HttpStatusCode.Accepted)
            }else{
                call.respondText("Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}