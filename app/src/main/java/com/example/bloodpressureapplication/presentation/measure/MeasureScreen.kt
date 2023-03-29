package com.example.bloodpressureapplication.presentation.measure

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.presentation.track.TrackContent
import com.example.bloodpressureapplication.presentation.track.TrackContentHeart
import com.example.bloodpressureapplication.util.Response

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MeasureScreen(
    navController : NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Measure", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                },
                actions = {

                },
                backgroundColor = Color.White,
                elevation = 10.dp
            )
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 5.dp,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Measure Blood Pressure",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(10.dp)
                        )

                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            shape = RoundedCornerShape(15.dp),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 5.dp,
                                pressedElevation = 7.dp,
                                disabledElevation = 0.dp
                            ),
                            onClick = { Log.d("ClickableButton", "Manual Blood Pressure Pressed") }
                        ) {
                            Text(text = AnnotatedString("Manual"), textAlign = TextAlign.Center)
                        }

                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            shape = RoundedCornerShape(15.dp),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 5.dp,
                                pressedElevation = 7.dp,
                                disabledElevation = 0.dp
                            ),
                            onClick = {
                                Log.d(
                                    "ClickableButton",
                                    "Automatic Blood Pressure Pressed"
                                )
                            }
                        ) {
                            Text(text = AnnotatedString("Automatic"), textAlign = TextAlign.Center)
                        }

                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 5.dp
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Measure Heart Rate",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(10.dp)
                        )

                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            shape = RoundedCornerShape(15.dp),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 5.dp,
                                pressedElevation = 7.dp,
                                disabledElevation = 0.dp
                            ),
                            onClick = { Log.d("ClickableButton", "Manual Heart Rate Pressed") }
                        ) {
                            Text(text = AnnotatedString("Manual"), textAlign = TextAlign.Center)
                        }

                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            shape = RoundedCornerShape(15.dp),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 5.dp,
                                pressedElevation = 7.dp,
                                disabledElevation = 0.dp
                            ),
                            onClick = { Log.d("ClickableButton", "Automatic Heart Rate Pressed") }
                        ) {
                            Text(text = AnnotatedString("Automatic"), textAlign = TextAlign.Center)
                        }
                    }

                }
            }
        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.MEASURE, navController = navController)
        }
    )
}