package com.example.bloodpressureapplication.presentation.track

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu
import com.example.bloodpressureapplication.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
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
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screens.TrackScreen.route)
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                actions = {

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {

                val openDialog = remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
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
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 5.dp,
                                pressedElevation = 7.dp,
                                disabledElevation = 0.dp
                            ),
                            onClick = { navController.navigate(Screens.MeasureBloodPressureScreen.route) }
                        ) {
                            Text(text = AnnotatedString("Manual"), textAlign = TextAlign.Center, fontSize = 20.sp)
                        }

                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )

                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            shape = RoundedCornerShape(15.dp),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 5.dp,
                                pressedElevation = 7.dp,
                                disabledElevation = 0.dp
                            ),
                            onClick = {
                                openDialog.value = true
                            }
                        ) {
                            Text(text = AnnotatedString("Automatic"), textAlign = TextAlign.Center, fontSize = 20.sp)
                        }

                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )

                        if (openDialog.value) {
                            AlertDialog(
                                onDismissRequest = {
                                    openDialog.value = false
                                },
                                dismissButton = {
                                    TextButton(
                                        onClick = {
                                            openDialog.value = false
                                        }
                                    ) {
                                        Text("Dismiss")
                                    }
                                },
                                confirmButton = {
                                    TextButton(onClick = {
                                        openDialog.value = false
                                    })
                                    { Text(text = "OK") }
                                },
                                title = { Text(text = "Feature Unavailable") },
                                text = { Text(text = "This feature has not yet been implemented. Check back in a later update.") }
                            )
                        }

                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
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
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 5.dp,
                                pressedElevation = 7.dp,
                                disabledElevation = 0.dp
                            ),
                            onClick = { navController.navigate(Screens.MeasureHeartRateScreen.route) }
                        ) {
                            Text(text = AnnotatedString("Manual"), textAlign = TextAlign.Center, fontSize = 20.sp)
                        }

                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )

                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            shape = RoundedCornerShape(15.dp),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 5.dp,
                                pressedElevation = 7.dp,
                                disabledElevation = 0.dp
                            ),
                            onClick = {
                                openDialog.value = true
                            }
                        ) {
                            Text(text = AnnotatedString("Automatic"), textAlign = TextAlign.Center, fontSize = 20.sp)
                        }

                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )

                        if (openDialog.value) {
                            AlertDialog(
                                onDismissRequest = {
                                    openDialog.value = false
                                },
                                dismissButton = {
                                    TextButton(
                                        onClick = {
                                            openDialog.value = false
                                        }
                                    ) {
                                        Text("Dismiss")
                                    }
                                },
                                confirmButton = {
                                    TextButton(onClick = {
                                        openDialog.value = false
                                    })
                                    { Text(text = "OK") }
                                },
                                title = { Text(text = "Feature Unavailable") },
                                text = { Text(text = "This feature has not yet been implemented. Check back in a later update.") }
                            )
                        }
                    }

                }
            }
        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.TRACK, navController = navController)
        }
    )
}
