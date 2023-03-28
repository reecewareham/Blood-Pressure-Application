package com.example.bloodpressureapplication.presentation.measure

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
            
        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.MEASURE, navController = navController)
        }
    )
}