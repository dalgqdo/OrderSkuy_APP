package com.example.orderskuy.model

import java.io.Serializable

class UserModel(
    var key: String,
    var id: String,
    var username: String,
    var password: String,
    var email: String,
    var profile: String
) : Serializable {
    constructor() : this("", "", "", "", "", "")
}