package com.example.orderskuy.model

import java.io.Serializable

data class OrderModel(
    var key: String? = "",
    var name: String? = "",
    var startTime: String? = "",
    var endTime: String? = "",
    var seat: String? = "",
    var status: String? = "",
    var userid: String? = "",
    var orderid: String? = "",
    var rating: String? = ""
) : Serializable
