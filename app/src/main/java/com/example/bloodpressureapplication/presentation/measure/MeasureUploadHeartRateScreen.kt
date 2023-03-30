package com.example.bloodpressureapplication.presentation.measure

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chargemap.compose.numberpicker.ListItemPicker
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.bloodpressureapplication.presentation.*
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens
import com.google.firebase.Timestamp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MeasureHeartRateScreen(
    navController : NavController
) {

    val heartRateViewModel: HeartRateReadingsViewModel = hiltViewModel()

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
                    var bpmState by remember {
                        mutableStateOf(0)
                    }
                    val possibleStatuses = listOf("Resting", "Exercise", "Active")
                    var statusState by remember {
                        mutableStateOf(possibleStatuses[0])
                    }

                    Text(
                        text = "Input Heart Rate Reading",
                        modifier = Modifier
                            .padding(10.dp),
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif
                    )

                    NumberPicker(
                        value = bpmState,
                        range = 0..200,
                        onValueChange = {
                            bpmState = it
                        }
                    )

                    ListItemPicker(
                        label = { it },
                        value = statusState,
                        onValueChange = {
                            statusState = it
                        },
                        list = possibleStatuses
                    )

                    Button(
                        onClick = {
                            var timestamp = Timestamp.now()
                            heartRateViewModel.uploadHeartReading(
                                bpm = bpmState,
                                readingStatus = statusState,
                                timestamp = timestamp
                            )
                        },
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Text(text = "Submit")
                        when (val response = heartRateViewModel.uploadHeartRateReadingData.value) {
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
        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.MEASURE, navController = navController)
        }
    )
}