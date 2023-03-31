package com.example.bloodpressureapplication.presentation.info

import com.example.bloodpressureapplication.R
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InfoScreen(
    navController : NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Info", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                },
                actions = {
                },
                backgroundColor = Color.White,
                elevation = 10.dp
            )
        },
        content = {
            InfoGrid()
        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.INFO, navController = navController)
        }
    )
}

@Composable
fun InfoGrid() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp),

        ) {
        Row(
            modifier = Modifier
            ,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(5.dp)
            ) {
                InfoCard(
                    painter = painterResource(id = R.drawable.placeholder_image),
                    contentDescription = "What is Blood Pressure?",
                    title = "What is Blood Pressure?"
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                InfoCard(
                    painter = painterResource(id = R.drawable.placeholder_image),
                    contentDescription = "Understanding Blood Pressure Numbers",
                    title = "Understanding Blood Pressure Numbers"
                )
            }

        }

        Row(
            modifier = Modifier
            ,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(5.dp)
            ) {
                InfoCard(
                    painter = painterResource(id = R.drawable.placeholder_image),
                    contentDescription = "How to Measure Blood Pressure?",
                    title = "How to Measure Blood Pressure?"
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                InfoCard(
                    painter = painterResource(id = R.drawable.placeholder_image),
                    contentDescription = "Blood Pressure Myths and Mistakes",
                    title = "Blood Pressure Myths and Mistakes"
                )
            }

        }

        Row(
            modifier = Modifier
            ,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(5.dp)
            ) {
                InfoCard(
                    painter = painterResource(id = R.drawable.placeholder_image),
                    contentDescription = "What is Hypertension?",
                    title = "What is Hypertension?"
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                InfoCard(
                    painter = painterResource(id = R.drawable.placeholder_image),
                    contentDescription = "What is Hypotension?",
                    title = "What is Hypotension?"
                )
            }

        }

        Row(
            modifier = Modifier
            ,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(5.dp)
            ) {
                InfoCard(
                    painter = painterResource(id = R.drawable.placeholder_image),
                    contentDescription = "How to Prevent Hypertension?",
                    title = "How to Prevent Hypertension?"
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                InfoCard(
                    painter = painterResource(id = R.drawable.placeholder_image),
                    contentDescription = "How to Prevent Hypotension?",
                    title = "How to Prevent Hypotension?"
                )
            }

        }

        Row(
            modifier = Modifier
            ,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(25.dp)
            ) {
            }

        }
    }
}
@Composable
fun InfoCard(
    painter: Painter,
    contentDescription: String,
    title: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Image(painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )

            Text(
                text = title,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(12.dp),
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }

}
/*


InfoCard(
painter = painterResource(id = R.drawable.cat),
contentDescription = "What is Hypertension?",
title = "What is Hypertension?"
)
InfoCard(
painter = painterResource(id = R.drawable.cat),
contentDescription = "What is Hypotension?",
title = "What is Hypotension?"
)

InfoCard(
painter = painterResource(id = R.drawable.cat),
contentDescription = "How to Prevent Hypertension?",
title = "How to Prevent Hypertension?"
)
InfoCard(
painter = painterResource(id = R.drawable.cat),
contentDescription = "How to Prevent Hypotension?",
title = "How to Prevent Hypotension?"
)*/