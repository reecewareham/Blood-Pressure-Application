package com.example.bloodpressureapplication.presentation.measure

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu

@Composable
fun MeasureScreen(
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
            Text(text = "Measure Screen")
        }
        BottomNavigationMenu(selectedItem = BottomNavigationItem.MEASURE, navController = navController)
    }
}