package com.example.bloodpressureapplication.presentation.track

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bloodpressureapplication.domain.model.BloodPressureReadings
import com.example.bloodpressureapplication.domain.model.HeartRateReadings
import com.example.bloodpressureapplication.presentation.*
import com.example.bloodpressureapplication.util.Response
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.*
import java.util.Calendar

var systolicValues = arrayListOf<Float>()
var diastolicValues = arrayListOf<Float>()
var dateValues = arrayListOf<String>()
var bloodPressureGraph = false

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TrackScreen(
    navController : NavController
) {
    val bloodPressureViewModel : BloodPressureReadingsViewModel = hiltViewModel()
    bloodPressureViewModel.getLast5Readings()

    val heartRateViewModel : HeartRateReadingsViewModel = hiltViewModel()
    heartRateViewModel.getLast5HeartReadings()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Track", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                },
                actions = {

                },
                backgroundColor = Color.White,
                elevation = 10.dp
            )
        },
        content = {
            Column () {
                when (val response = bloodPressureViewModel.bloodPressure5ReadingData.value) {
                    is Response.Loading -> {
                        CircularProgressIndicator()
                    }
                    is Response.Success -> {
                        val obj = response.data
                        Column(

                        ) {
                            TrackContent(obj)
                        }
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
                        Column(

                        ) {
                            TrackContentHeart(obj)
                        }
                    }
                    is Response.Error -> {
                        Toast(message = response.message)
                    }
                }
            }


        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.TRACK, navController = navController)
        }
    )
}

@Composable
fun TrackContent(bloodPressureReadings: List<BloodPressureReadings>) {
    LazyColumn(
    ) {
        items(items = bloodPressureReadings, itemContent =  {
            ListContent(it)
            bloodPressureGraph = true
        })
    }

    if (bloodPressureGraph) {
        Card(
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth(0.8f)

        ) {
            fun getSys() = List(5) { FloatEntry(it.toFloat(), systolicValues.elementAt(it)) }
            fun getDia() = List(5) { FloatEntry(it.toFloat(), diastolicValues.elementAt(it)) }
            val chartEntryModel = ChartEntryModelProducer(getSys(), getDia())
            Chart(
                chart = lineChart(),
                chartModelProducer = chartEntryModel,
                startAxis = startAxis(),
                bottomAxis = bottomAxis(),
            )
        }
    }
}

@Composable
fun ListContent(it: BloodPressureReadings) {
    val sys = it.systolicPressure.toFloat()
    val dia = it.diastolicPressure.toFloat()

    systolicValues.add(0,sys)
    diastolicValues.add(0,dia)

    Text(text = it.systolicPressure.toString())
    Text(text = it.diastolicPressure.toString())

    var test = Calendar.getInstance()
    test.time = it.timestamp?.toDate()
    var date = (test.get(Calendar.DAY_OF_MONTH).toString()) + "/" + ((test.get(Calendar.MONTH) + 1).toString())

    dateValues.add(0,date)

}

@Composable
fun TrackContentHeart(heartRateReadings: List<HeartRateReadings>) {
    LazyColumn {
        items(items = heartRateReadings, itemContent =  {
            ListContentHeart(it)
        })
    }

}

@Composable
fun ListContentHeart(it: HeartRateReadings) {
    Text(text = it.userId)
    Text(text = it.bpm.toString())
    Text(text = it.readingStatus)
    Text(text = it.timestamp?.toDate().toString())
}
