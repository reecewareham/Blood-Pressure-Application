package com.example.bloodpressureapplication.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
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
import com.example.bloodpressureapplication.presentation.profile.UserViewModel
import com.example.bloodpressureapplication.presentation.track.*
import com.example.bloodpressureapplication.util.Response
import java.util.*

var systolicValue = arrayListOf<String>()
var diastolicValue = arrayListOf<String>()
var gotBloodReading = false
var bpmValue = arrayListOf<String>()
var statusValue = arrayListOf<String>()
var gotHeartReading = false

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
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
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding()),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(1f)
                ) {
                    when (val response = userViewModel.getUserData.value) {
                        is Response.Loading -> {
                            CircularProgressIndicator()
                        }
                        is Response.Success -> {
                            val obj = response.data

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp),
                                    shape = RoundedCornerShape(15.dp),
                                    elevation = CardDefaults.cardElevation(),
                                ) {
                                    if (obj != null) {
                                        Text(
                                            text = "Hi, " + obj.firstName,
                                            fontWeight = FontWeight.Bold,
                                            lineHeight = 20.sp,
                                            fontSize = 40.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .align(Alignment.CenterHorizontally)
                                        )
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
                        elevation = CardDefaults.cardElevation()
                    ) {
                        Text(
                            text = "Latest Readings",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.CenterHorizontally)
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
                                        elevation = CardDefaults.cardElevation()
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        ) {
                                            Text(
                                                text = "Systolic:",
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 25.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .padding(10.dp)
                                                    .align(Alignment.CenterHorizontally)
                                            )

                                            Text(
                                                text = systolicValue[0],
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 40.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .padding(10.dp)
                                                    .align(Alignment.CenterHorizontally)
                                            )

                                            Text(
                                                text = "Diastolic:",
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 25.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .padding(10.dp)
                                                    .align(Alignment.CenterHorizontally)
                                            )

                                            Text(
                                                text = diastolicValue[0],
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 40.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .padding(10.dp)
                                                    .align(Alignment.CenterHorizontally)
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
                                        elevation = CardDefaults.cardElevation()
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        ) {
                                            Text(
                                                text = "BPM:",
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 25.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.padding(10.dp)
                                            )

                                            Text(
                                                text = bpmValue[0],
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 40.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.padding(10.dp)
                                            )

                                            Text(
                                                text = "Status:",
                                                fontWeight = FontWeight.Bold,
                                                lineHeight = 20.sp,
                                                fontSize = 25.sp,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.padding(10.dp)
                                            )

                                            Text(
                                                text = statusValue[0],
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
                    }
                    Column {
                        if (gotBloodReading) {
                            HomeCheckBlood()
                        }
                        if (gotHeartReading) {
                            HomeCheckHeart()
                        }
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
fun HomeCheckBlood() {
    val colour = checkReading(systolicValue[0].toInt(), diastolicValue[0].toInt())
    val text = checkReadingText(systolicValue[0].toInt(), diastolicValue[0].toInt())


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(),
                colors = CardDefaults.cardColors(containerColor = colour)
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp)
                )

            }
}

@Composable
fun HomeCheckHeart() {
    val colourHeart = checkHeartReading(bpmValue[0].toInt(), statusValue[0])
    val textHeart = checkHeartReadingText(bpmValue[0].toInt(), statusValue[0])


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(),
            colors = CardDefaults.cardColors(containerColor = colourHeart)
        ) {
            Text(
                text = textHeart,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )

        }
}