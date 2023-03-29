package com.example.bloodpressureapplication.presentation.home

import androidx.compose.foundation.layout.*
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
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.presentation.profile.UserViewModel
import com.example.bloodpressureapplication.util.Response


@Composable
fun HomeScreen(
    navController : NavController
) {
    val userViewModel: UserViewModel = hiltViewModel()
    userViewModel.getUserInfo()

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

                        HomeReadings()

                        HomeCheck()

                        /*val chartEntryModel = entryModelOf(entriesOf(4f, 12f, 8f, 16f), entriesOf(12f,16f,4f,12f))

                        Chart(
                            chart = lineChart(),
                            model = chartEntryModel,
                            startAxis = startAxis(),        VICO GRAPH
                            bottomAxis = bottomAxis()
                        )*/
                    }
                        BottomNavigationMenu(
                            selectedItem = BottomNavigationItem.HOME,
                            navController = navController
                        )
                }
            }
        }
        is Response.Error -> {
            Toast(message = response.message)
        }
    }
}

@Composable
fun HomeReadings() {
    Row(

    ) {
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
                    text = "100",
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
                    text = "70",
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

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
                    text = "110",
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
                    text = "Resting",
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

@Composable
fun HomeCheck() {
    Column(

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp,
            backgroundColor = Color.Green
        ) {
            Text(
                text = "Your blood pressure is healthy. Keep it up.",
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