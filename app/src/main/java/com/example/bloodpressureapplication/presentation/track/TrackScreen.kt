package com.example.bloodpressureapplication.presentation.track

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bloodpressureapplication.R
import com.example.bloodpressureapplication.domain.model.BloodPressureReadings
import com.example.bloodpressureapplication.domain.model.HeartRateReadings
import com.example.bloodpressureapplication.presentation.*
import com.example.bloodpressureapplication.presentation.info.TabRowItem
import com.example.bloodpressureapplication.presentation.profile.UserViewModel
import com.example.bloodpressureapplication.ui.theme.md_theme_light_primary
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens
import com.patrykandpatrick.vico.core.entry.*
import kotlinx.coroutines.launch

var systolic5Values = arrayListOf<Float>()
var diastolic5Values = arrayListOf<Float>()
var date5Values = arrayListOf<String>()
var bloodPressureGraph = false
var bloodChartEntryModel = ChartEntryModelProducer()
var bloodPressureReadings = listOf<BloodPressureReadings>()
var hasBlood5Readings = false
var hasBloodReadings = false

var bpm5Values = arrayListOf<Float>()
var status5Values = arrayListOf<String>()
var date5HeartValues = arrayListOf<String>()
var heartRateGraph = false
var heartChartEntryModel = ChartEntryModelProducer()
var heartRateReadings = listOf<HeartRateReadings>()
var hasHeart5Readings = false
var hasHeartReadings = false
var userAge = 0

var complete = false


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrackScreen(
    navController : NavController
) {
    val userViewModel : UserViewModel = hiltViewModel()
    val bloodPressureViewModel : BloodPressureReadingsViewModel = hiltViewModel()
    val heartRateViewModel : HeartRateReadingsViewModel = hiltViewModel()

    GetReadings(userViewModel, bloodPressureViewModel, heartRateViewModel)

    if (complete) {

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

                val pagerState = rememberPagerState()
                val coroutineScope = rememberCoroutineScope()
                val tabRowItems = listOf(
                    TabRowItem(
                        title = "Blood Pressure",
                        icon = R.drawable.bloodpressure,
                        screen = { BloodPressureTrack() }
                    ),
                    TabRowItem(
                        title = "Heart Rate",
                        icon = R.drawable.heartrate,
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
                                    Image(
                                        painterResource(id = item.icon),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(md_theme_light_primary)
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
            },
            bottomBar = {
                BottomNavigationMenu(
                    selectedItem = BottomNavigationItem.TRACK,
                    navController = navController
                )
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
}

@Composable
fun GetReadings(userViewModel: UserViewModel, bloodPressureViewModel : BloodPressureReadingsViewModel, heartRateViewModel: HeartRateReadingsViewModel) {
    userViewModel.getUserInfo()
    bloodPressureViewModel.getAllReadings()
    heartRateViewModel.getAllHeartReadings()

    when (val response = userViewModel.getUserData.value) {
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {
            if (response.data != null) {
                val obj = response.data
                userAge = obj.age.toInt()
                when (val response = bloodPressureViewModel.bloodPressureReadingData.value) {
                    is Response.Loading -> {
                        CircularProgressIndicator()
                    }
                    is Response.Success -> {
                        val obj = response.data
                        bloodPressureReadings = obj
                        bloodTrackContent(bloodPressureReadings)
                        when (val response = heartRateViewModel.heartRateReadingData.value) {
                            is Response.Loading -> {
                                CircularProgressIndicator()
                            }
                            is Response.Success -> {
                                val obj = response.data
                                heartRateReadings = obj
                                heartTrackContent(heartRateReadings)
                                complete = true
                            }
                            is Response.Error -> {
                                Toast(message = response.message)
                            }
                        }
                    }
                    is Response.Error -> {
                        Toast(message = response.message)
                    }
                }
            }
        }
        is Response.Error -> {
            Toast(message = response.message)
        }
    }
}

@Composable
fun BloodPressureTrack() {
        Column(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
        ) {
            Card(

            ) {
                BloodPressureGraph(chartEntryModelProducer = bloodChartEntryModel)
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
                    if (systolic5Values.size == 0) {
                        Color.White
                    } else {
                        checkReading(
                            systolic5Values[systolic5Values.size - 1].toInt(),
                            diastolic5Values[diastolic5Values.size - 1].toInt()
                        )
                    }
                )
            ) {
                Text(
                    text = if (systolic5Values.size == 0) {
                        "You have not entered any readings yet. Press the plus button in the bottom right to get started."
                    } else { checkReadingText(
                        systolic5Values[systolic5Values.size - 1].toInt(),
                        diastolic5Values[diastolic5Values.size - 1].toInt()
                    )},
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


            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BloodListOfReadings(bloodPressureReadings)
            }
        }
}

@Composable
fun HeartRateTrack() {
        Column(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
        ) {
            Card(

            ) {
                HeartRateGraph(chartEntryModelProducer = heartChartEntryModel)
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
                    if (bpm5Values.size == 0) {
                        Color.White
                    } else {
                        checkHeartReading(
                            bpm5Values[bpm5Values.size - 1].toInt(),
                            status5Values[status5Values.size - 1],
                            userAge
                        )
                    }
                )
            ) {
                Text(
                    text = if (bpm5Values.size == 0) {
                        "You have not entered any readings yet. Press the plus button in the bottom right to get started."
                    } else { checkHeartReadingText(
                        bpm5Values[bpm5Values.size - 1].toInt(),
                        status5Values[status5Values.size - 1],
                        userAge
                    )},
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


            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                HeartListOfReadings(heartRateReadings)
            }
        }
}

