package com.example.bloodpressureapplication.presentation.info

import android.annotation.SuppressLint
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatIsBPScreen(
    navController : NavController
) {
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
                            text = "What is Blood Pressure?",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Blood pressure is the measurement of force that your heart uses to pump blood all around your body through your arteries." +
                                "When your heart pumps, it uses the force to push blood out through your arteries into places where your body needs it such as your cells and tissues." +
                        "It does this roughly between 60 and 100 times a minute, 24 hours a day.",
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
                            text = "Why Does Blood Pressure Change?",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Your blood pressure is constantly changing based on the things you're doing. When you are excited or exercising," +
                                "your blood pressure raises. When you are resting, it lowers.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "Your blood pressure is also affected by things such as:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "\u2022" + " Age",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Medications",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Changes in position",
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
                            text = "Why Does Blood Pressure Matter?",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "High blood pressure is commonly referred to as the \"silent killer\" as it usually" +
                         " has no symptoms. It can damage vital organs such as your heart and brain before you know anything is wrong.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "It is a major risk factor for cardiovascular disease and without treatment, it can cause:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "\u2022" + " Heart attack",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Stroke",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Heart failure",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Transient ischemic attack (TIA)",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Kidney disease",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Peripheral artery disease",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Aneurysms",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Enlarged heart",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Broken blood vessels",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
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
                            text = "Who is at Risk of High Blood Pressure?",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "You are at higher risk of high blood pressure if you:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "\u2022" + " Have a family history of high blood pressure, diabetes or cardiovascular disease.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Are black",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Have obesity",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Have diabetes",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Are age 60 and above",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Have high cholesterol",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Don't exercise often",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Use tobacco products.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Have a high intake of salt",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Use oral contraceptives",
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BPNumbersScreen(
    navController: NavController
) {
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
                            text = "How is Blood Pressure Measured?",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Blood pressure is measured using a blood pressure monitor. They often have an arm cuff attached " +
                        "to them. This is wrapped around your upper arm and filled with air until it is tight. The blood pressure " +
                        "monitor will then show 2 values after taken its measurements using sensors in the arm cuff. Blood pressure is " +
                        "measured in millimetres of mercury (mmHg). The higher number is the systolic pressure and the lower number is " +
                        "diastolic pressure. An example of a normal reading would be 100/70mmHg.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 10.dp,
                            bottom = it.calculateBottomPadding() + 10.dp
                        )
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
                            text = "What do the Numbers Mean?",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Blood pressure is measured using two numbers called systolic pressure and diastolic pressure. " +
                        "These numbers determine the current status of your blood pressure and indicate if you are healthy or not.",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "The ranges that determine your blood pressure status can be found below:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(width = 50.dp, height = 50.dp)
                        ) {
                            drawCircle(
                                color = Color(0xFF3aeaf0),
                                radius = 50f
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Column(
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "Hypotension",
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 20.sp,
                                    fontSize = 22.sp,
                                    textAlign = TextAlign.Left
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "SYS < 90 or DIA < 60",
                                    lineHeight = 20.sp,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Left
                                )
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(width = 50.dp, height = 50.dp)
                        ) {
                            drawCircle(
                                color = Color.Green,
                                radius = 50f
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Column(
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "Normal",
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 20.sp,
                                    fontSize = 22.sp,
                                    textAlign = TextAlign.Left
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "SYS 90-119 and DIA 60-79",
                                    lineHeight = 20.sp,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Left
                                )
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(width = 50.dp, height = 50.dp)
                        ) {
                            drawCircle(
                                color = Color.Yellow,
                                radius = 50f
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Column(
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "Elevated",
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 20.sp,
                                    fontSize = 22.sp,
                                    textAlign = TextAlign.Left
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "SYS 120-129 and DIA 60-79",
                                    lineHeight = 20.sp,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Left
                                )
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(width = 50.dp, height = 50.dp)
                        ) {
                            drawCircle(
                                color = Color(0xFFFFB52E),
                                radius = 50f
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Column(
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "Hypertension - Stage 1",
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 20.sp,
                                    fontSize = 22.sp,
                                    textAlign = TextAlign.Left
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "SYS 130-139 and DIA 80-89",
                                    lineHeight = 20.sp,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Left
                                )
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(width = 50.dp, height = 50.dp)
                        ) {
                            drawCircle(
                                color = Color.Red,
                                radius = 50f
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Column(
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "Hypertension - Stage 2",
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 20.sp,
                                    fontSize = 22.sp,
                                    textAlign = TextAlign.Left
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "SYS 140-180 and DIA 90-120",
                                    lineHeight = 20.sp,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Left
                                )
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(width = 50.dp, height = 50.dp)
                        ) {
                            drawCircle(
                                color = Color(0xFF8c0f0f),
                                radius = 50f
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Column(
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "Hypertensive Crisis",
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 20.sp,
                                    fontSize = 22.sp,
                                    textAlign = TextAlign.Left
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = "SYS > 180 or DIA > 120",
                                    lineHeight = 20.sp,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Left
                                )
                            }
                        }
                    }
                }
            }

        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.INFO, navController = navController)
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ControlBPScreen(
    navController: NavController
) {
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
                            text = "Healthy Weight",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Weight and blood pressure are linked directly to each other. As your weight increases, your blood pressure also increases. " +
                        "If you're overweight or have obesity, losing a small amount of weight can positively effect your blood pressure. " +
                        "The size of your waistline is also important is carrying too much weight can increase the risk of high blood pressure.",
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
                            text = "Limit Alcohol",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Limiting alcohol to less than one drink a day can help lower blood pressure. Drinking too much alcohol will raise blood " +
                        "pressure and can also reduce the effectiveness of blood pressure medications.",
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
                            text = "Quit Smoking",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Smoking is one of the biggest factors for high blood pressure. Every cigarette leads to an instant " +
                        "response of the heart, increasing blood pressure. Smoking will damage vessel walls and block up arteries meaning " +
                        "the heart has to do more work than normal. Smoking also negatively effects some medications.",
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
                            text = "Exercise Regularly",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Exercising regularly can help reduce blood pressure by up to 10mmHG. You should aim to do at least 30 minutes of " +
                        "physical activity a day. Some examples of activities to help reduce blood pressure include walking, jogging, " +
                        "cycling, swimming, dancing and strength training.",
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
                            text = "Manage Stress",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Long term stress can contribute to a raised blood pressure. Finding ways to help reduce stress " +
                        "can also in turn help reduce blood pressure. Finding the causes of stress and ways of reducing it can be very beneficial. " +
                        "Some suggestions are avoid trying to do too much, avoid stress triggers, make time to relax and focus on issues " +
                        "you can control and find ways to solve them.",
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BPMythsScreen(
    navController: NavController
) {
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
                            text = "I Don't Have Symptoms, So I Don't Have High Blood Pressure",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "This is simply not true when it comes to blood pressure. High blood pressure is commonly known " +
                        "as the \"silent killer\" as it generally doesn't have any symptoms until it's too late.",
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
                            text = "I'm Young, So I Don't Have To Worry",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "High blood pressure doesn't just affect older people, but younger people too. It's true that it's more common " +
                        "in older people, but it doesn't mean young people are immune from it.",
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
                            text = "If My Blood Pressure Decreases, I Don't Need to Continue Taking Medication",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "You should continue taking your blood pressure medication as high blood pressure is a lifelong condition " +
                        "and medications don't magically cure it. If you stop taking your medication, it will rise once again.",
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
                            text = "My Blood Pressure is Low, I Don't Have to Worry",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Low blood pressure can be just as dangerous as high blood pressure as it can mean " +
                        "that blood is not getting around your body properly. If you begin to feel fatigued and feel fainting, " +
                        "consult a medical professional.",
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatIsHyperScreen(
    navController: NavController
) {
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
                    .padding(start = 10.dp, end = 10.dp, top = it.calculateTopPadding(), bottom = it.calculateBottomPadding() + 10.dp)
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
                            text = "What is Hypertension?",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Hypertension is when your blood pressure reading is higher than 140/90mmHg. Hypertension generally " +
                        "doesn't have any noticeable symptoms, but if left untreated could lead to serious problems such as heart attacks and strokes. " ,
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "Some symptoms that can appear during a hypertensive crisis are as followed:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "\u2022" + " Headache",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Dizziness",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Have obesity",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Nausea",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Are age 60 and above",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Have high cholesterol",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Don't exercise often",
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
                        text = "\u2022" + " Chest pain",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Blurred vision",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Nosebleed",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "If you get any of these symptoms, you should consult a medical professional immediately. " +
                        "Some of these symptoms could mean a heart attack or a stroke.",
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatIsHypoScreen(
    navController: NavController
) {
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
                    .padding(start = 10.dp, end = 10.dp, top = it.calculateTopPadding(), bottom = 10.dp)
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
                            text = "What is Hypotension?",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Hypotension is when your blood pressure reading is lower than 90/60mmHg. Hypotension generally " +
                                "doesn't always have symptoms, but if left untreated could lead to serious problems. " ,
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "Some symptoms that can appear during a hypotensive crisis are as followed:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "\u2022" + " Lightheartedness or dizziness",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Feeling sick",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Blurred vision",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Generally feeling weak",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Confusion",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Fainting",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "If you get any of these symptoms, you should consult a medical professional immediately. ",
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
                                text = "Causes of Low Blood Pressure",
                                fontWeight = FontWeight.Bold,
                                lineHeight = 20.sp,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(10.dp)
                            )
                        }

                        Text(
                            text = "The causes of low blood pressure can vary depending on the time of day. What you're doing and how you are feeling " +
                            "can also affect it. The causes could be because you're fit and healthy or you may have inherited it from parents. " +
                            "Some other causes can be being pregnant, some medical conditions like diabetes and some medicines.",
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreventHyperScreen(
    navController: NavController
) {
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
                    .padding(start = 10.dp, end = 10.dp, top = it.calculateTopPadding(), bottom = 10.dp)
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
                            text = "Treatment for Hypertension",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Doctors can help keep your blood pressure at a safe level by using lifestyle changes and medicines. " +
                        "Treatments work differently for each person so discussing it with your doctor is advised.",
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
                            text = "Lifestyle Changes",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Below are some lifestyle changes that can help reduce blood pressure:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "\u2022" + " Cut back on alcohol",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Lose weight if overweight",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Reduce your salt intake and eat healthier",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Exercise more often",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Cut down on caffeine",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Stop smoking",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
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
                            text = "Medicines",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "Below are some medicines that can help reduce blood pressure:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "\u2022" + " ACE inhibitors - Enalapril and Lisinopril",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Calcium channel blockers - Amlodipine and Felodipine",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Diuretics - Indapamide and Bendroflumethiazide",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Angiotensin-2 receptor blockers (ARBs) - Candesartan and Irbesartan",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Beta blockers - Atenolol and Bisoprolol",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Alpha blockers - Doaxzosin",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "\u2022" + " Other diuretics - Amiloride and Spironolactone",
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreventHypoScreen(
    navController: NavController
) {
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
                    .padding(start = 10.dp, end = 10.dp, top = it.calculateTopPadding(), bottom = it.calculateBottomPadding() + 10.dp)
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
                            text = "Treatment for Hypotension",
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Text(
                        text = "If a cause for low blood pressure can be found, a doctor will recommend treatments to easy symptoms. They may " +
                                "suggest treatments such as:",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(10.dp)
                    )

                    Text(
                        text = "\u2022" + " Changing your medication or altering the dosage if that is found to be the cause",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "\u2022" + " Suggesting you to wear support stockings which improve circulation and increase your blood pressure",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "\u2022" + " Suggest you to use more salt",
                        lineHeight = 20.sp,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "\u2022" + " Suggest you to drink more water",
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
