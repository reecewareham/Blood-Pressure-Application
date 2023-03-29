package com.example.bloodpressureapplication.presentation.measure

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UploadBloodPressureScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Measure Manually", fontWeight = FontWeight.Bold, fontSize = 24.sp)
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
            //BottomNavigationMenu(selectedItem = BottomNavigationItem.MEASURE, navController = navController)
        }
    )
}