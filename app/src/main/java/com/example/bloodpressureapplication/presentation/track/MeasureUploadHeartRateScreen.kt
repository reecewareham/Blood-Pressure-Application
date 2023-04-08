package com.example.bloodpressureapplication.presentation.reminders

import android.annotation.SuppressLint
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chargemap.compose.numberpicker.ListItemPicker
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.bloodpressureapplication.presentation.*
import com.example.bloodpressureapplication.ui.theme.gray
import com.example.bloodpressureapplication.ui.theme.red
import com.example.bloodpressureapplication.ui.theme.redScaffold
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens
import com.google.firebase.Timestamp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MeasureHeartRateScreen(
    navController : NavController
) {

    val heartRateViewModel: HeartRateReadingsViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Measure Manually", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screens.MeasureScreen.route)
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon", tint = Color.White)
                    }
                },
                actions = {

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = redScaffold
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
                        .padding(10.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var bpmState by remember {
                            mutableStateOf(100)
                        }
                        val possibleStatuses = listOf("Resting", "Exercise", "Active")
                        var statusState by remember {
                            mutableStateOf(possibleStatuses[1])
                        }

                        Text(
                            text = "Input Heart Rate Reading",
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
                            Card(
                                modifier = Modifier
                                    .padding(5.dp),
                                elevation = CardDefaults.cardElevation(5.dp),
                                colors = CardDefaults.cardColors(containerColor = gray)
                            ) {
                                Column() {
                                    Text(
                                        text = "BPM",
                                        modifier = Modifier
                                            .padding(10.dp),
                                        fontSize = 20.sp,
                                        textAlign = TextAlign.Center
                                    )

                                    NumberPicker(
                                        value = bpmState,
                                        range = 0..200,
                                        onValueChange = {
                                            bpmState = it
                                        },
                                        dividersColor = Color(0xFFBA1926),
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                            }

                            Card(
                                modifier = Modifier
                                    .padding(5.dp),
                                elevation = CardDefaults.cardElevation(5.dp),
                                colors = CardDefaults.cardColors(containerColor = gray)
                            ) {
                                Column() {
                                    Text(
                                        text = " Status",
                                        modifier = Modifier
                                            .padding(10.dp),
                                        fontSize = 20.sp,
                                        textAlign = TextAlign.Center
                                    )

                                    ListItemPicker(
                                        label = { it },
                                        value = statusState,
                                        onValueChange = {
                                            statusState = it
                                        },
                                        list = possibleStatuses,
                                        dividersColor = Color(0xFFBA1926),
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                        }

                        Button(
                            onClick = {
                                val timestamp = Timestamp.now()
                                heartRateViewModel.uploadHeartReading(
                                    bpm = bpmState,
                                    readingStatus = statusState,
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
                                heartRateViewModel.uploadHeartRateReadingData.value) {
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