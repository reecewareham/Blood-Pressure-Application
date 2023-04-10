package com.example.bloodpressureapplication.presentation.info

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu
import com.example.bloodpressureapplication.ui.theme.redScaffold
import com.example.bloodpressureapplication.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatIsHRScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "Information", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screens.InfoScreen.route)

                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon", tint = Color.White)
                    }
                },
                actions = {
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = redScaffold
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding() + 10.dp, start = 10.dp, end = 10.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "What is Heart Rate?",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Your heart rate is the number of times your heart beats per minute. Everyone's heart rate is different and can " +
                        "change based on the circumstance.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "A normal resting adult heart rate is between 60 and 100 BPM. This can change every minute and what's normal for you "+
                        "may not be normal for someone else as it depends on age, health, lifestyle and exercise.  ",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "A dangerous heart rate is when the heart beats too slowly, quickly or irregularly. This is known as an arrhythmia. "+
                        "These can be dangerous because blood isn't pumped around the body properly which can cause symptoms such as dizziness and fainting. "+
                        "It can also increase the risk of a stroke. There are two main categories of arrhythmia: tachycardia which is a faster than normal "+
                        "heart rate and bradycardia which is a slower than normal heart rate.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "\u2022" + " Tachycardia - A faster than normal heart rate",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Bradycardia - A slower than normal heart rate",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }

        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.INFO, navController = navController)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HRNumbersScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "Information", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screens.InfoScreen.route)

                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon", tint = Color.White)
                    }
                },
                actions = {
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = redScaffold
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .verticalScroll(rememberScrollState())
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "How do I Measure My Heart Rate?",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "You can measure your heart rate yourself by checking your pulse, however for a more accurate "+
                        "and easier result, you can use a heart rate monitor that checks it for you. Both methods are useful for monitoring "+
                        "your health, however they cannot diagnose conditions so consult a doctor if you notice anything unusual.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = it.calculateBottomPadding() + 10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Heart Rate Numbers",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Heart rate is measured in beats per minute (BPM). The healthy range for you heart rate changes based on many factors"+
                        ", but mostly is influenced by activity. The normal resting heart rate should reside between 60 and 100 BPM. "+
                        "Your target heart rate can change based on the activity you are doing. If you are performing a moderate intensity activity, "+
                        "then your heart rate should reside between 50-70% of your maximum heart rate.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "If you are performing a moderate intensity activity, "+
                                "then your heart rate should reside between 50-70% of your maximum heart rate.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "If you are performing a vigorous intensity activity, "+
                                "then your heart rate should reside between 70-85% of your maximum heart rate.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "Your maximum heart rate can be calculated by taking your age from 220. For example, if you are 20 "+
                        "years old, you would take 20 from 220 which gives you a maximum heart rate of 200 BPM.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.INFO, navController = navController)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HRFactorsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "Information", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screens.InfoScreen.route)

                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon", tint = Color.White)
                    }
                },
                actions = {
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = redScaffold
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .verticalScroll(rememberScrollState())
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Factors Affecting Heart Rate",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "There are many factors that can affect your heart rate throughout your day. Below are some of the most common examples:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Body Position",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Your heart rate generally doesn't change too much when switching body positions, however if you move position rapidly, " +
                        "your heart rate will increase for the first 20 seconds.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Emotions",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Being too emotional after experiencing a stressful situation will raise your heart rate temporarily.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Air Temperature",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "The temperature around you can impact your heart rate. If it is too cold or too hot, your body has to pump more " +
                        "blood around your body to your skin to heat you or cool you. This can increase your heart rate by up to 10 BPM.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Alcohol",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Drinking lots of alcohol per day can increase your heart rate and cause you to have irregular heart beats.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Caffeine and Nicotine",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Caffeine and nicotine both can raise your heart rate after intaking it. Within an hour of intaking caffeine, its effect will reach " +
                        "it's peak and won't return to normal until after 4 hours.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Age",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "With age, your resting heart rate will not change but your training heart rate will noticeably become slower.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Gender",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "The average heart rate of women is higher than men however, woman have a lower risk of cardiac diseases.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Exercise",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Regular exercise will decrease your resting heart rate.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Medications",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "The side effects of lots of medications can directly affect your heart rate. Some medications may increase your heart rate and some may lower it.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = it.calculateBottomPadding() + 10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Body Size",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "People who suffer from obesity can have a slightly higher pulse than a fit and healthy person.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.INFO, navController = navController)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HRComplicationsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "Information", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screens.InfoScreen.route)

                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon", tint = Color.White)
                    }
                },
                actions = {
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = redScaffold
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .verticalScroll(rememberScrollState())
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Heart Rate Complications",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Your heart rate generally stays at healthy range but in some cases, it can exceed the normal range whether it " +
                        "be exercise or under stress.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Tachycardia",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Tachycardia is the term used for a heart rate over 100 beats a minute. There are many different types of " +
                        "irregular heart rhythms that can cause tachycardia. A high heart rate isn't always an issue, for example during exercise or stress." +
                        "Tachycardia doesn't always cause symptoms or complications but if it is left untreated it can form serious health problems " +
                        "such as heart failure and stroke.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Types of Tachycardia",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "There are many different types of tachycardia. Some of the more common types of tachycardia caused " +
                        "by heart rhythms include:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "\u2022" + " Atrial fibrillation (A-fib) - Most common type of tachycardia. Chaotic, irregular "+
                        "electrical signals in the upper chamber of the heart cause a fast heartbeat. Some episodes won't end without treatment.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Atrial flutter - Similar to A-fib, but heartbeats are more organised. Some episodes won't end without treatment."+
                        "People with atrial flutter often have A-fib.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Ventricular tachycardia - Starts in the lower heart chambers (ventricles). Rapid heart rate doesn't " +
                        "allow the ventricles to fill and contract to pump enough blood to the rest of the body. Some episodes may be brief and cause no harm, " +
                        "but some may last long and be life-threatening.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Supraventricular tachycardia (SVT) - This is a broad term for arrhythmias that start above the ventricles." +
                        " This causes episodes of a pounding heartbeat that begin and end abruptly.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Ventricular fibrillation - Rapid electrical signals that cause the ventricles to quiver instead of contracting in coordination. " +
                        "This problem can lead to death if the heart rhythm isn't returned to normal within minutes. Most people diagnosed "+
                        "with this have underlying heart disease or have experience serious trauma to the heart.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Symptoms of Tachycardia",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "The general signs and symptoms of tachycardia are as follows:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "\u2022" + " Sensation of a racing, pounding heartbeat or flopping in the chest (palpitations)",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Fainting (syncope)",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Lightheadedness",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Chest pain",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Shortness of breath",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Rapid pulse rate",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "If you experience many of these symptoms and your heart rate feels fast, seek medical help immediately. "+
                        "You could be experiencing a serious problem which could lead to a stroke or heart failure.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Bradycardia",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Bradycardia is the term used for a heart rate that is slow. This means that it is less than 60 BPM. "+
                        "Bradycardia can be a serious issue if the heart rate is too slow and can't pump blood around the body properly. " +
                        "Sometimes there are no symptoms or complications, but if it becomes severe, an implanted pacemaker may be needed to help " +
                        "the heart maintain a proper rate.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = it.calculateBottomPadding() + 10.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Canvas(
                            modifier = Modifier
                                .size(width = 10.dp, height = 35.dp)
                        ) {
                            drawRoundRect(
                                color = Color.Red,
                                size = Size(width = 10.dp.toPx(), height = 35.dp.toPx()),
                                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(1.dp)
                        )
                        Text(
                            text = "Symptoms of Bradycardia",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "A slower heart rate can prevent the brain and other organs from getting enough oxygen which " +
                        "can cause the symptoms found below:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "\u2022" + " Confusion or memory problems",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "\u2022" + " Chest pain",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "\u2022" + " Fatigue",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "\u2022" + " Shortness of breath",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "\u2022" + " Dizziness or lightheadedness",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "\u2022" + " Fainting (syncope) or near-fainting",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "\u2022" + " Easily tiring during physical activity",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "Many things can cause these symptoms of bradycardia, but if you faint and have difficulty " +
                        "breathing and have chest pain lasting longer than a few minutes, seek medical attention.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.INFO, navController = navController)
        }
    )
}

