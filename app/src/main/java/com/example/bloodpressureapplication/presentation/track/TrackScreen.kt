package com.example.bloodpressureapplication.presentation.track

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu

@Composable
fun TrackScreen(
    navController : NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(text = "Track Screen")
        }
        BottomNavigationMenu(selectedItem = BottomNavigationItem.TRACK, navController = navController)
    }
}