package com.example.bloodpressureapplication.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bloodpressureapplication.util.Screens


enum class BottomNavigationItem(
    val icon: ImageVector,
    val route: Screens
) {
    REMINDERS(Icons.Default.Notifications, Screens.RemindersScreen),
    TRACK(Icons.Default.Analytics, Screens.TrackScreen),
    HOME(Icons.Default.Home, Screens.HomeScreen),
    INFO(Icons.Default.Info, Screens.InfoScreen),
    PROFILE(Icons.Default.Person, Screens.ProfileScreen)
}

@Composable
fun BottomNavigationMenu(
    selectedItem : BottomNavigationItem, navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp))
    ) {
        for(item in BottomNavigationItem.values()) {

            Image(
                imageVector = item.icon,
                contentDescription = "ImageItem",
                modifier = Modifier
                    .size(50.dp)
                    .weight(1f)
                    .padding(5.dp)
                    .clickable {
                        navController.navigate(item.route.route)
                    },
                colorFilter = if(item == selectedItem) ColorFilter.tint(Color.Red)
                else ColorFilter.tint(Color.Gray)
            )

        }
    }
}