package com.example.bloodpressureapplication.registerlogin

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bloodpressureapplication.MainScreen

@Composable
fun ScreenMain(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = LoginRegisterRoutes.Login.route) {

        composable(LoginRegisterRoutes.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(LoginRegisterRoutes.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable(LoginRegisterRoutes.Main.route) {
            MainScreen()
        }
    }
}