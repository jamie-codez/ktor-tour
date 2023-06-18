package com.code.jamie

import com.code.jamie.plugins.configureRouting
import com.code.jamie.plugins.configureSerialization
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class OrderRouteTests {
    @Test
    fun testGetOrder() = testApplication {
        application {
            configureRouting()
            configureSerialization()
        }
        val response = client.get("/order/2023-04-03-01/")
        assertEquals(
            """{"number":"2023-04-03-01","contents":[{"item":"Cheese Burger","amount":1,"price":8.50},{"item":"Water","amount":2,"price":1.50},{"item":"Coke","amount":2,"price":1.76},{"item":"Ice Cream","amount":1,"price":2.35}]}""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK,response.status)
    }
}