package com.example.bloodpressureapplication.presentation.track

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chargemap.compose.numberpicker.ListItemPicker
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.bloodpressureapplication.presentation.*
import com.example.bloodpressureapplication.ui.theme.gray
import com.example.bloodpressureapplication.ui.theme.redScaffold
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditHeartRateScreen(
    navController: NavHostController,
    heartRateReadingId : String,
    bpm : Int,
    status : String
) {

    val heartRateViewModel: HeartRateReadingsViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Edit Reading", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screens.TrackScreen.route)

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
                                    val bpmState = remember {
                                        mutableStateOf(bpm)
                                    }
                                    val possibleStatuses = listOf("Resting", "Exercise", "Active")
                                    val readingStatusState = remember {
                                        mutableStateOf(status)
                                    }



                                    Text(
                                        text = "Edit Heart Rate Reading",
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
                                                    value = bpmState.value,
                                                    range = 0..200,
                                                    onValueChange = {
                                                        bpmState.value = it
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
                                                    value = readingStatusState.value,
                                                    onValueChange = {
                                                        readingStatusState.value = it
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
                                            heartRateViewModel.updateHeartReading(
                                                heartRateReadingId = heartRateReadingId,
                                                bpm = bpmState.value,
                                                readingStatus = readingStatusState.value
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
                                        Text(text = "Edit Reading", fontSize = 20.sp)

                                        when (val response =
                                            heartRateViewModel.updateHeartRateReadingData.value) {
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
                                                            popUpTo(Screens.EditHeartRateScreen.route) {
                                                                inclusive = true
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            is Response.Error -> {
                                                Toast(message = response.message)
                                            }
                                        }
                                    }

                                    Button(
                                        onClick = {
                                            heartRateViewModel.deleteHeartReading(
                                                heartRateReadingId = heartRateReadingId
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
                                        Text(text = "Delete Reading", fontSize = 20.sp)

                                        when (val response =
                                            heartRateViewModel.deleteHeartRateReadingData.value) {
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
                                                            popUpTo(Screens.EditHeartRateScreen.route) {
                                                                inclusive = true
                                                            }
                                                        }
                                                    }
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