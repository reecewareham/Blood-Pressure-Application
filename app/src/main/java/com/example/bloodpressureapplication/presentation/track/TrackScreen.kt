package com.example.bloodpressureapplication.presentation.track

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
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
import com.example.bloodpressureapplication.presentation.authentication.AuthenticationViewModel
import com.example.bloodpressureapplication.presentation.profile.UserViewModel
import com.example.bloodpressureapplication.util.Response
import java.lang.reflect.Modifier

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
            Divider(color = Color.Black)
        })
    }
}

@Composable
fun ListContent(it: BloodPressureReadings) {
    Text(text = it.userId)
    Text(text = it.systolicPressure.toString())
    Text(text = it.diastolicPressure.toString())
    Text(text = it.timestamp?.toDate().toString())
}

@Composable
fun TrackContentHeart(heartRateReadings: List<HeartRateReadings>) {
    LazyColumn {
        items(items = heartRateReadings, itemContent =  {
            ListContentHeart(it)
            Divider(color = Color.Black)
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
