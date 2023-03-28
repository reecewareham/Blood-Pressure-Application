package com.example.bloodpressureapplication.presentation.info

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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

        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.INFO, navController = navController)
        }
    )
}