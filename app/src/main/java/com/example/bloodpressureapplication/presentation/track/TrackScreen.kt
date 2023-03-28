package com.example.bloodpressureapplication.presentation.track

import android.annotation.SuppressLint
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
import com.example.bloodpressureapplication.presentation.BloodPressureReadingsViewModel
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.presentation.authentication.AuthenticationViewModel
import com.example.bloodpressureapplication.presentation.profile.UserViewModel
import com.example.bloodpressureapplication.util.Response

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TrackScreen(
    navController : NavController
) {
    val bloodPressureViewModel : BloodPressureReadingsViewModel = hiltViewModel()
    bloodPressureViewModel.getAllReadings()

    when (val response = bloodPressureViewModel.bloodPressureReadingData.value) {
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {
            val obj = response.data

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
                    TrackContent(obj)
                },
                bottomBar = {
                    BottomNavigationMenu(selectedItem = BottomNavigationItem.TRACK, navController = navController)
                }
            )
        }
        is Response.Error -> {
            Toast(message = response.message)
        }
    }
}

@Composable
fun TrackContent(bloodPressureReadings: List<BloodPressureReadings>) {
    LazyColumn {
        items(items = bloodPressureReadings, itemContent =  {
            ListContent(it)
        })
    }
}

@Composable
fun ListContent(it: BloodPressureReadings) {
    Text(text = it.userId)
    Text(text = it.systolicPressure.toString())
    Text(text = it.diastolicPressure.toString())
    Text(text = it.timestamp.toString())
}
