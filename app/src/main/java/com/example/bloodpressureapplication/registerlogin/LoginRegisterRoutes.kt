package com.example.bloodpressureapplication.registerlogin

sealed class LoginRegisterRoutes(val route: String) {

    object Login : LoginRegisterRoutes("Login")
    object Register : LoginRegisterRoutes("Register")
    object Main : LoginRegisterRoutes("Main")
}
