package com.example.bloodpressureapplication.presentation.track

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.bloodpressureapplication.presentation.info.TabRowItem
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens
import com.patrykandpatrick.vico.core.entry.*
import kotlinx.coroutines.launch
import java.util.Calendar

var systolic5Values = arrayListOf<Float>()
var diastolic5Values = arrayListOf<Float>()
var date5Values = arrayListOf<String>()
var bloodPressureGraph = false
var bloodChartEntryModel = ChartEntryModelProducer()
var bloodPressure5Readings = listOf<BloodPressureReadings>()
var bloodPressureReadings = listOf<BloodPressureReadings>()
var hasBlood5Readings = false
var hasBloodReadings = false

var bpm5Values = arrayListOf<Float>()
var status5Values = arrayListOf<String>()
var date5HeartValues = arrayListOf<String>()
var heartRateGraph = false
var heartChartEntryModel = ChartEntryModelProducer()
var heartRate5Readings = listOf<HeartRateReadings>()
var heartRateReadings = listOf<HeartRateReadings>()
var hasHeart5Readings = false
var hasHeartReadings = false

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrackScreen(
    navController : NavController
) {
    val bloodPressureViewModel : BloodPressureReadingsViewModel = hiltViewModel()
    bloodPressureViewModel.getLast5Readings()
    bloodPressureViewModel.getAllReadings()

    val heartRateViewModel : HeartRateReadingsViewModel = hiltViewModel()
    heartRateViewModel.getLast5HeartReadings()
    heartRateViewModel.getAllHeartReadings()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Track", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                },
                actions = {

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        content = {

            when (val response = bloodPressureViewModel.bloodPressure5ReadingData.value) {
                is Response.Loading -> {
                    CircularProgressIndicator()
                }
                is Response.Success -> {
                    val obj = response.data
                        bloodPressure5Readings = obj
                        hasBlood5Readings = true
                }
                is Response.Error -> {
                    Toast(message = response.message)
                }
            }

            when (val response = bloodPressureViewModel.bloodPressureReadingData.value) {
                is Response.Loading -> {
                    CircularProgressIndicator()
                }
                is Response.Success -> {
                    val obj = response.data
                        bloodPressureReadings = obj
                        hasBloodReadings = true
                }
                is Response.Error -> {
                    Toast(message = response.message)
                }
            }

            when (val response = heartRateViewModel.heartRate5ReadingData.value) {
                is Response.Loading -> {
                    CircularProgressIndicator()
                }
                is Response.Success -> {
                    val obj = response.data
                    heartRate5Readings = obj
                    hasHeart5Readings = true
                }
                is Response.Error -> {
                    Toast(message = response.message)
                }
            }

            when (val response = heartRateViewModel.heartRateReadingData.value) {
                is Response.Loading -> {
                    CircularProgressIndicator()
                }
                is Response.Success -> {
                    val obj = response.data
                    heartRateReadings = obj
                    hasHeartReadings = true
                }
                is Response.Error -> {
                    Toast(message = response.message)
                }
            }

            if (hasBlood5Readings && hasBloodReadings) {
                BloodTrackContent(bloodPressure5Readings)
                HeartTrackContent(heartRate5Readings)

                val pagerState = rememberPagerState()
                val coroutineScope = rememberCoroutineScope()
                val tabRowItems = listOf(
                    TabRowItem(
                        title = "Blood Pressure",
                        icon = Icons.Default.Home,
                        screen = { BloodPressureTrack() }
                    ),
                    TabRowItem(
                        title = "Heart Rate",
                        icon = Icons.Default.Home,
                        screen = { HeartRateTrack() }
                    )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = it.calculateTopPadding())
                ) {
                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        indicator = { tabPosition ->
                            TabRowDefaults.Indicator(
                                height = 5.dp,
                                modifier = Modifier.tabIndicatorOffset(tabPosition[pagerState.currentPage])
                            )
                        }
                    ) {
                        tabRowItems.forEachIndexed { index, item ->
                            Tab(
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = ""
                                    )
                                },
                                text = {
                                    Text(text = item.title)
                                }
                            )
                        }
                    }

                    HorizontalPager(pageCount = tabRowItems.size, state = pagerState) {
                        tabRowItems[pagerState.currentPage].screen()
                    }
                }
            }
    },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.TRACK, navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.MeasureScreen.route) },
            ) {
                Icon(Icons.Filled.Add, "Add Reading")
            }
        }
    )
}

@Composable
fun BloodPressureTrack() {

    Column (
        modifier = Modifier
            .padding(10.dp)
    ) {
        if (bloodPressureGraph) {
            Card(

            ) {
                BloodPressureGraph(chartEntryModelProducer = bloodChartEntryModel)
            }
        }
        Spacer(
            modifier = Modifier
                .padding(5.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(),
            colors = CardDefaults.cardColors(
                checkReading(
                    systolic5Values[4].toInt(),
                    diastolic5Values[4].toInt()
                ))
        ) {
            Text(
                text = checkReadingText(
                    systolic5Values[4].toInt(),
                    diastolic5Values[4].toInt()
                ),
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )

        }

        Spacer(
            modifier = Modifier
                .padding(5.dp)
        )

        if(bloodPressureGraph) {

            Card (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BloodListOfReadings(bloodPressureReadings)
            }
        }
    }
}

@Composable
fun HeartRateTrack() {
    Column (
        modifier = Modifier
            .padding(10.dp)
    ) {
        if (heartRateGraph) {
            Card(

            ) {
                HeartRateGraph(chartEntryModelProducer = heartChartEntryModel)
            }
        }
        Spacer(
            modifier = Modifier
                .padding(5.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(),
            colors = CardDefaults.cardColors(
                Color.Red)
        ) {
            Text(
                text = "test",
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )

        }

        Spacer(
            modifier = Modifier
                .padding(5.dp)
        )

        if(heartRateGraph) {

            Card (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                HeartListOfReadings(heartRateReadings)
            }
        }
    }
}

