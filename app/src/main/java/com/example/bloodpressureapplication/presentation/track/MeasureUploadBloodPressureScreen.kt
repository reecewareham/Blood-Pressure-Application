package com.example.bloodpressureapplication.presentation.reminders

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.bloodpressureapplication.presentation.BloodPressureReadingsViewModel
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.ui.theme.md_theme_light_primary
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens
import com.google.firebase.Timestamp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
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
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screens.MeasureScreen.route)
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                actions = {

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var systolicState by remember {
                            mutableStateOf(100)
                        }
                        var diastolicState by remember {
                            mutableStateOf(100)
                        }

                        Text(
                            text = "Input Blood Pressure Reading",
                            modifier = Modifier
                                .padding(10.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column() {
                                Text(
                                    text = "Systolic",
                                    modifier = Modifier
                                        .padding(10.dp),
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center
                                )

                                NumberPicker(
                                    value = systolicState,
                                    range = 0..300,
                                    onValueChange = {
                                        systolicState = it
                                    },
                                    dividersColor = md_theme_light_primary,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                )
                            }

                            Column() {
                                Text(
                                    text = "Diastolic",
                                    modifier = Modifier
                                        .padding(10.dp),
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center
                                )

                                NumberPicker(
                                    value = diastolicState,
                                    range = 0..300,
                                    onValueChange = {
                                        diastolicState = it
                                    },
                                    dividersColor = md_theme_light_primary,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                )
                            }
                        }

                        Button(
                            onClick = {
                                val timestamp = Timestamp.now()
                                bloodPressureViewModel.uploadReading(
                                    systolicPressure = systolicState,
                                    diastolicPressure = diastolicState,
                                    timestamp = timestamp
                                )
                            },
                            modifier = Modifier
                                .padding(10.dp),
                            shape = RoundedCornerShape(15.dp),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 5.dp,
                                pressedElevation = 7.dp,
                                disabledElevation = 0.dp
                            ),
                        ) {
                            Text(text = "Submit", fontSize = 20.sp)
                            when (val response =
                                bloodPressureViewModel.uploadBloodPressureReadingData.value) {
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
                                        Toast(message = "Upload Failed")
                                    }
                                }
                                is Response.Error -> {
                                    Toast(message = response.message)
                                }
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.TRACK, navController = navController)
        }
    )
}