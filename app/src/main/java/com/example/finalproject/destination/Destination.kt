package com.example.finalproject.destination

open class Destination(val route: String) {
    object Stats: Destination("stats")
    object Edit: Destination("edit")
    object Practice: Destination("practice")
}
