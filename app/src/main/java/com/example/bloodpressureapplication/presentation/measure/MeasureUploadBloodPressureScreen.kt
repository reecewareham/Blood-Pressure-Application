package com.example.bloodpressureapplication.presentation.measure

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.bloodpressureapplication.R
import com.example.bloodpressureapplication.presentation.BloodPressureReadingsViewModel
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.presentation.profile.UserViewModel
import com.example.bloodpressureapplication.presentation.track.TrackContent
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens
import com.google.firebase.Timestamp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MeasureBloodPressureScreen(
    navController : NavController
) {

    val bloodPressureViewModel: BloodPressureReadingsViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Measure Manually", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                },
                actions = {

                },
                backgroundColor = Color.White,
                elevation = 10.dp
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var systolicState by remember {
                        mutableStateOf(0)
                    }
                    var diastolicState by remember {
                        mutableStateOf(0)
                    }

                    Text(
                        text = "Input Blood Pressure Reading",
                        modifier = Modifier
                            .padding(10.dp),
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif
                    )

                    NumberPicker(
                        value = systolicState,
                        range = 0..300,
                        onValueChange = {
                            systolicState = it
                        }
                    )

                    NumberPicker(
                        value = diastolicState,
                        range = 0..300,
                        onValueChange = {
                            diastolicState = it
                        }
                    )

                    Button(
                        onClick = {
                            var timestamp = Timestamp.now()
                                bloodPressureViewModel.uploadReading(
                                    systolicPressure = systolicState,
                                    diastolicPressure = diastolicState,
                                    timestamp = timestamp
                                )
                        },
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Text(text = "Submit")
                        when (val response = bloodPressureViewModel.uploadBloodPressureReadingData.value) {
                            is Response.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                            }
                            is Response.Success -> {
                                if (response.data) {
                                    LaunchedEffect(key1 = true) {
                                        navController.navigate(Screens.TrackScreen.route) {
                                            popUpTo(Screens.MeasureScreen.route) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                } else {
                                    Toast(message = "Sign up failed")
                                }
                            }
                            is Response.Error -> {
                                Toast(message = response.message)
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.MEASURE, navController = navController)
        }
    )
}