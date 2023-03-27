package com.example.bloodpressureapplication.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.bloodpressureapplication.BloodPressureReadingsViewModel
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.presentation.profile.ProfileExportFile
import com.example.bloodpressureapplication.presentation.profile.UserViewModel
import com.example.bloodpressureapplication.presentation.profile.components.MyProfile
import com.example.bloodpressureapplication.presentation.profile.components.RoundedImage
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens


@Composable
fun HomeScreen(
    navController : NavController
) {
    val userViewModel: UserViewModel = hiltViewModel()
    userViewModel.getUserInfo()
    val bloodPressureReadingsViewModel: BloodPressureReadingsViewModel = hiltViewModel()
    bloodPressureReadingsViewModel.getUserBloodPressureReadings()

    when (val response = userViewModel.getUserData.value) {
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {
            if (response.data != null) {
                val obj = response.data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Home",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                )
                            },
                            actions = {

                            },
                            backgroundColor = Color.White,
                            elevation = 10.dp
                        )

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            shape = RoundedCornerShape(15.dp),
                            elevation = 5.dp,
                        ) {
                            Text(
                                text = "Hi, " + obj.firstName,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 20.sp,
                                fontSize = 40.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(10.dp)
                            )
                        }

                        /*val chartEntryModel = entryModelOf(entriesOf(4f, 12f, 8f, 16f), entriesOf(12f,16f,4f,12f))

                        Chart(
                            chart = lineChart(),
                            model = chartEntryModel,
                            startAxis = startAxis(),        VICO GRAPH
                            bottomAxis = bottomAxis()
                        )*/

                        BottomNavigationMenu(
                            selectedItem = BottomNavigationItem.PROFILE,
                            navController = navController
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