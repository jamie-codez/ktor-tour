package com.code.jamie.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(val id: String, val firstName: String, val lastName: String, val email: String)

val customerStorage = mutableListOf<Customer>()

@Serializable
data class Order(val number: String, val content: List<OrderItem>)

@Serializable
data class OrderItem(val item: String, val amount: Int, val price: Double)

val orderStorage = listOf(
    Order(
        "2023-04-06-01", listOf(
            OrderItem("Ham Sandwich", 2, 5.50),
            OrderItem("Water", 1, 1.50),
            OrderItem("Beer", 3, 2.30),
            OrderItem("CheeseCake", 1, 3.75)
        )
    ),
    Order(
        "2023-04-03-01", listOf(
            OrderItem("Cheese Burger", 1, 8.50),
            OrderItem("Water", 2, 1.50),
            OrderItem("Coke", 2, 1.76),
            OrderItem("Ice Cream", 1, 2.35)
        )
    )
)