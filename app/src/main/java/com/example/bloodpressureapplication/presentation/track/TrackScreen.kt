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
import com.example.bloodpressureapplication.ui.theme.red
import com.example.bloodpressureapplication.ui.theme.redScaffold
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens
import com.patrykandpatrick.vico.core.entry.*
import kotlinx.coroutines.launch

var systolicValues = arrayListOf<Float>()
var diastolicValues = arrayListOf<Float>()
var dateValues = arrayListOf<String>()
var systolicTemp = arrayListOf<Float>()
var diastolicTemp = arrayListOf<Float>()
var dateTemp = arrayListOf<String>()
var bloodChartEntryModel = ChartEntryModelProducer()
var bloodPressureReadings = listOf<BloodPressureReadings>()

var bpmValues = arrayListOf<Float>()
var statusValues = arrayListOf<String>()
var dateHeartValues = arrayListOf<String>()
var bpmTemp = arrayListOf<Float>()
var statusTemp = arrayListOf<String>()
var dateHeartTemp = arrayListOf<String>()
var heartChartEntryModel = ChartEntryModelProducer()
var heartRateReadings = listOf<HeartRateReadings>()

var userAge = 0
var complete = false

var bloodReadingEdit = ""
var sysEdit = 0
var diaEdit = 0
var heartReadingEdit = ""
var bpmEdit = 0
var statusEdit = ""


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
                        Text(text = "Track", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
                    },
                    actions = {

                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = redScaffold
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
                        screen = { BloodPressureTrack(navController) }
                    ),
                    TabRowItem(
                        title = "Heart Rate",
                        icon = R.drawable.heartrate,
                        screen = { HeartRateTrack(navController) }
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
                                        colorFilter = ColorFilter.tint(Color(0xFFBA1926))
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
                    navController = navController,
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(Screens.MeasureScreen.route) },
                    containerColor = red
                ) {
                    Icon(Icons.Filled.Add, "Add Reading", tint = Color.White)
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
                when (val response = bloodPressureViewModel.bloodPressureReadingsData.value) {
                    is Response.Loading -> {
                        CircularProgressIndicator()
                    }
                    is Response.Success -> {
                        val obj = response.data
                        bloodPressureReadings = obj
                        bloodTrackContent(bloodPressureReadings)
                        when (val response = heartRateViewModel.heartRateReadingsData.value) {
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
fun BloodPressureTrack(navController: NavController) {
        Column(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
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
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(
                    if (systolicValues.size == 0) {
                        Color.White
                    } else {
                        checkReading(
                            systolicValues[systolicValues.size - 1].toInt(),
                            diastolicValues[diastolicValues.size - 1].toInt()
                        )
                    }
                )
            ) {
                Text(
                    text = if (systolicValues.size == 0) {
                        "You have not entered any readings yet. Press the plus button in the bottom right to get started."
                    } else { checkReadingText(
                        systolicValues[systolicValues.size - 1].toInt(),
                        diastolicValues[diastolicValues.size - 1].toInt()
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
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                BloodListOfReadings(bloodPressureReadings, navController)
            }
        }
}

@Composable
fun HeartRateTrack(navController: NavController) {
        Column(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
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
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(
                    if (bpmValues.size == 0) {
                        Color.White
                    } else {
                        checkHeartReading(
                            bpmValues[bpmValues.size - 1].toInt(),
                            statusValues[statusValues.size - 1],
                            userAge
                        )
                    }
                )
            ) {
                Text(
                    text = if (bpmValues.size == 0) {
                        "You have not entered any readings yet. Press the plus button in the bottom right to get started."
                    } else { checkHeartReadingText(
                        bpmValues[bpmValues.size - 1].toInt(),
                        statusValues[statusValues.size - 1],
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
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                HeartListOfReadings(heartRateReadings, navController)
            }
        }
}

