package com.example.bloodpressureapplication.util

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object LoginScreen : Screens("login_screen")
    object SignUpScreen : Screens("signup_screen")
    object ProfileScreen : Screens("profile_screen")
    object HomeScreen : Screens("home_screen")
    object TrackScreen : Screens("track_screen")
    object MeasureScreen : Screens("measure_screen")
    object InfoScreen : Screens("info_screen")
    object MeasureBloodPressureScreen : Screens("blood_pressure_measure_screen")
    object MeasureHeartRateScreen : Screens("heart_rate_measure_screen")
    object ProfileEditScreen : Screens("profile_edit_screen")
}
