package com.example.bloodpressureapplication.presentation.info

import com.example.bloodpressureapplication.R
import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InfoScreen(
    navController : NavController
) {


    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "Information", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                },
                actions = {
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        content = {

            val pagerState = rememberPagerState()
            val coroutineScope = rememberCoroutineScope()
            val tabRowItems = listOf(
                TabRowItem(
                    title = "Blood Pressure",
                    icon = R.drawable.bloodpressure,
                    screen = { BloodPressureInfoGrid()}
                ),
                TabRowItem(
                    title = "Heart Rate",
                    icon = R.drawable.heartrate,
                    screen = { HeartRateInfoGrid() }
                )
            )

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())) {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    indicator = {tabPosition ->
                        TabRowDefaults.Indicator(
                            height = 5.dp,
                            modifier = Modifier.tabIndicatorOffset(tabPosition[pagerState.currentPage])
                        )
                    }
                ) {
                    tabRowItems.forEachIndexed {index, item ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            icon = {
                                Image(
                                    painterResource(id = item.icon),
                                    contentDescription = null
                                )

                            },
                            text = {
                                Text(text = item.title)
                            }
                        )
                    }
                }
                
                HorizontalPager(pageCount = tabRowItems.size, state = pagerState) {
                    tabRowItems[pagerState.currentPage].screen()
                }
            }
        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.INFO, navController = navController)
        }
    )
}

@Composable
fun BloodPressureInfoGrid() {
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
fun HeartRateInfoGrid() {
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
                    title = "test"
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
        elevation = CardDefaults.cardElevation()
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

data class TabRowItem(
    val title: String,
    val icon: Int,
    val screen: @Composable () -> Unit
)