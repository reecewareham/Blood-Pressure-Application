package com.example.bloodpressureapplication.presentation.reminders

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.presentation.home.*
import com.example.bloodpressureapplication.ui.theme.red
import com.example.bloodpressureapplication.ui.theme.redScaffold
import com.example.bloodpressureapplication.util.Response
import java.time.LocalDateTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen(
    navController : NavController
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Reminders", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
                },
                actions = {
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = redScaffold
                )
            )
        },
        content = {

        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.REMINDERS, navController = navController)
        }
    )

}