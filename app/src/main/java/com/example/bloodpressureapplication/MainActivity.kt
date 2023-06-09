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
import com.example.bloodpressureapplication.presentation.info.*
import com.example.bloodpressureapplication.presentation.profile.ProfileEditScreen
import com.example.bloodpressureapplication.presentation.profile.ProfileScreen
import com.example.bloodpressureapplication.presentation.reminders.RemindersScreen
import com.example.bloodpressureapplication.presentation.track.*
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
        composable(route = Screens.EditBloodPressureScreen.route) {
            EditBloodPressureScreen(navController = navController, bloodPressureReadingId = bloodReadingEdit, systolic = sysEdit, diastolic = diaEdit)
        }
        composable(route = Screens.EditHeartRateScreen.route) {
            EditHeartRateScreen(navController = navController, heartRateReadingId = heartReadingEdit, bpm = bpmEdit, status = statusEdit)
        }
        composable(route = Screens.WhatIsBPScreen.route) {
            WhatIsBPScreen(navController = navController)
        }
        composable(route = Screens.BPNumbersScreen.route) {
            BPNumbersScreen(navController = navController)
        }
        composable(route = Screens.ControlBPScreen.route) {
            ControlBPScreen(navController = navController)
        }
        composable(route = Screens.BPMythsScreen.route) {
            BPMythsScreen(navController = navController)
        }
        composable(route = Screens.WhatIsHyperScreen.route) {
            WhatIsHyperScreen(navController = navController)
        }
        composable(route = Screens.WhatIsHypoScreen.route) {
            WhatIsHypoScreen(navController = navController)
        }
        composable(route = Screens.PreventHyperScreen.route) {
            PreventHyperScreen(navController = navController)
        }
        composable(route = Screens.PreventHypoScreen.route) {
            PreventHypoScreen(navController = navController)
        }
        composable(route = Screens.WhatIsHRScreen.route) {
            WhatIsHRScreen(navController = navController)
        }
        composable(route = Screens.HRNumbersScreen.route) {
            HRNumbersScreen(navController = navController)
        }
        composable(route = Screens.HRFactorsScreen.route) {
            HRFactorsScreen(navController = navController)
        }
        composable(route = Screens.HRComplicationsScreen.route) {
            HRComplicationsScreen(navController = navController)
        }
    }
}