package com.example.bloodpressureapplication.domain.model

////////////////////////////////////////////////////////////////////
/*
User Model. Specifies the model used for users.
*/
////////////////////////////////////////////////////////////////////

data class User(
    var userId: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var age: String = "",
    var imageUrl: String = ""
)
