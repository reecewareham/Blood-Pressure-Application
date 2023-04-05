package com.example.bloodpressureapplication

import com.example.bloodpressureapplication.ui.theme.BloodPressureApplicationTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bloodpressureapplication.presentation.authentication.AuthenticationViewModel
import com.example.bloodpressureapplication.presentation.authentication.LoginScreen
import com.example.bloodpressureapplication.presentation.authentication.SignUpScreen
import com.example.bloodpressureapplication.presentation.home.HomeScreen
import com.example.bloodpressureapplication.presentation.SplashScreen
import com.example.bloodpressureapplication.presentation.info.InfoScreen
import com.example.bloodpressureapplication.presentation.reminders.MeasureBloodPressureScreen
import com.example.bloodpressureapplication.presentation.reminders.MeasureHeartRateScreen
import com.example.bloodpressureapplication.presentation.reminders.MeasureScreen
import com.example.bloodpressureapplication.presentation.profile.ProfileEditScreen
import com.example.bloodpressureapplication.presentation.profile.ProfileScreen
import com.example.bloodpressureapplication.presentation.reminders.RemindersScreen
import com.example.bloodpressureapplication.presentation.track.TrackScreen
import com.example.bloodpressureapplication.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodPressureApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val authViewModel : AuthenticationViewModel = hiltViewModel()
                    BloodPressureAppScreen(navController, authenticationViewModel = authViewModel)
                }
            }
        }
    }
}


@Composable
fun BloodPressureAppScreen(navController: NavHostController, authenticationViewModel : AuthenticationViewModel) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController = navController, authenticationViewModel)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController = navController, authenticationViewModel)
        }
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController, authenticationViewModel)
        }
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screens.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = Screens.TrackScreen.route) {
            TrackScreen(navController = navController)
        }
        composable(route = Screens.MeasureScreen.route) {
            MeasureScreen(navController = navController)
        }
        composable(route = Screens.InfoScreen.route) {
            InfoScreen(navController = navController)
        }
        composable(route = Screens.MeasureBloodPressureScreen.route) {
            MeasureBloodPressureScreen(navController = navController)
        }
        composable(route = Screens.MeasureHeartRateScreen.route) {
            MeasureHeartRateScreen(navController = navController)
        }
        composable(route = Screens.ProfileEditScreen.route) {
            ProfileEditScreen(navController = navController)
        }
        composable(route = Screens.RemindersScreen.route) {
            RemindersScreen(navController = navController)
        }
    }
}