package com.example.bloodpressureapplication.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bloodpressureapplication.domain.model.BloodPressureReadings
import com.example.bloodpressureapplication.domain.model.HeartRateReadings
import com.example.bloodpressureapplication.presentation.*
import com.example.bloodpressureapplication.presentation.info.InfoGrid
import com.example.bloodpressureapplication.presentation.profile.UserViewModel
import com.example.bloodpressureapplication.presentation.track.*
import com.example.bloodpressureapplication.presentation.track.ComposeChart6
import com.example.bloodpressureapplication.util.Response
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import java.util.*

var systolicValue = arrayListOf<String>()
var diastolicValue = arrayListOf<String>()
var gotBloodReading = false
var bpmValue = arrayListOf<String>()
var statusValue = arrayListOf<String>()
var gotHeartReading = false

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController : NavController
) {
    val userViewModel: UserViewModel = hiltViewModel()
    userViewModel.getUserInfo()

    val bloodViewModel: BloodPressureReadingsViewModel = hiltViewModel()
    bloodViewModel.getLastReading()

    val heartViewModel: HeartRateReadingsViewModel = hiltViewModel()
    heartViewModel.getLastHeartReading()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                },
                actions = {
                },
                backgroundColor = Color.White,
                elevation = 10.dp
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (val response = userViewModel.getUserData.value) {
                        is Response.Loading -> {
                            CircularProgressIndicator()
                        }
                        is Response.Success -> {
                            val obj = response.data
                            Column(

                            ) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp),
                                    shape = RoundedCornerShape(15.dp),
                                    elevation = 5.dp,
                                ) {
                                    if (obj != null) {
                                        Text(
                                            text = "Hi, " + obj.firstName,
                                            fontWeight = FontWeight.Bold,
                                            lineHeight = 20.sp,
                                            fontSize = 40.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.padding(10.dp)
                                        )
                                    }
                                }
                            }
                        }
                        is Response.Error -> {
                            Toast(message = response.message)
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(10.dp),
                        shape = RoundedCornerShape(15.dp),
                        elevation = 5.dp
                    ) {
                        Text(
                            text = "Latest Reading",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Row {

                        when (val response = bloodViewModel.bloodPressureLastReadingData.value) {
                            is Response.Loading -> {
                                CircularProgressIndicator()
                            }
                            is Response.Success -> {
                                val obj = response.data
                                GetLastBloodReading(obj)
                                if (gotBloodReading) {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth(0.5f)
                                            .padding(10.dp),
                                        shape = RoundedCornerShape(15.dp),
                                        elevation = 5.dp
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Systolic:",
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 35.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.padding(10.dp)
                                            )

                                            Text(
                                                text = systolicValue[0],
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 30.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.padding(10.dp)
                                            )

                                            Text(
                                                text = "Diastolic:",
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 35.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.padding(10.dp)
                                            )

                                            Text(
                                                text = diastolicValue[0],
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 30.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.padding(10.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            is Response.Error -> {
                                Toast(message = response.message)
                            }
                        }

                        when (val response = heartViewModel.heartRateLastReadingData.value) {
                            is Response.Loading -> {
                                CircularProgressIndicator()
                            }
                            is Response.Success -> {
                                val obj = response.data
                                GetLastHeartReading(obj)
                                if (gotHeartReading) {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        shape = RoundedCornerShape(15.dp),
                                        elevation = 5.dp
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "BPM:",
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 35.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.padding(10.dp)
                                            )

                                            Text(
                                                text = bpmValue[0],
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 30.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.padding(10.dp)
                                            )

                                            Text(
                                                text = "Status:",
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 35.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.padding(10.dp)
                                            )

                                            Text(
                                                text = statusValue[0],
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 30.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.padding(10.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            is Response.Error -> {
                                Toast(message = response.message)
                            }
                        }
                    }
                    if (gotBloodReading && gotHeartReading) {
                        HomeCheck()
                    }

                }
            }
        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.HOME, navController = navController)
        }
    )
}

@Composable
fun GetLastBloodReading(bloodPressureReadings: List<BloodPressureReadings>) {
    LazyColumn(
    ) {
        items(items = bloodPressureReadings, itemContent =  {
            AddToBloodList(it)
            gotBloodReading = true
        })
    }
}

@Composable
fun AddToBloodList(it: BloodPressureReadings) {
    val sys = it.systolicPressure.toString()
    val dia = it.diastolicPressure.toString()

    systolicValue.add(0,sys)
    diastolicValue.add(0,dia)
}

@Composable
fun GetLastHeartReading(heartRateReadings: List<HeartRateReadings>) {
    LazyColumn(
    ) {
        items(items = heartRateReadings, itemContent =  {
            AddToHeartList(it)
            gotHeartReading = true
        })
    }
}

@Composable
fun AddToHeartList(it: HeartRateReadings) {
    val bpm = it.bpm.toString()
    val status = it.readingStatus

    bpmValue.add(0,bpm)
    statusValue.add(0,status)
}

@Composable
fun HomeCheck() {
        var colour = checkReading(systolicValue[0].toInt(), diastolicValue[0].toInt())
        var text = checkReadingText(systolicValue[0].toInt(), diastolicValue[0].toInt())
        Column(

        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 5.dp,
                backgroundColor = colour
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp)
                )

            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 5.dp,
                backgroundColor = Color(0xFFFFB52E)
            ) {
                Text(
                    text = "Your heart rate is too fast. Check ways to reduce it in the info section.",
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp)
                )

            }
        }
}