package com.example.bloodpressureapplication.presentation.profile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.bloodpressureapplication.domain.model.BloodPressureReadings
import com.example.bloodpressureapplication.domain.model.HeartRateReadings
import com.example.bloodpressureapplication.presentation.*
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.presentation.authentication.AuthenticationViewModel
import com.example.bloodpressureapplication.presentation.profile.components.MyProfile
import com.example.bloodpressureapplication.presentation.profile.components.RoundedImage
import com.example.bloodpressureapplication.presentation.track.*
import com.example.bloodpressureapplication.ui.theme.red
import com.example.bloodpressureapplication.ui.theme.redScaffold
import com.example.bloodpressureapplication.util.Screens
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import java.io.FileOutputStream
import java.io.OutputStream

var bloodPressureReadingsCSV = listOf<BloodPressureReadings>()
var heartRateReadingsCSV = listOf<HeartRateReadings>()

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController : NavController
) {
    val userViewModel: UserViewModel = hiltViewModel()
    val authViewModel: AuthenticationViewModel = hiltViewModel()
    userViewModel.getUserInfo()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Profile", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
                },
                actions = {
                    Button(
                        onClick = {
                            authViewModel.signOut()
                        },
                        shape = RoundedCornerShape(15.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 5.dp,
                            pressedElevation = 7.dp,
                            disabledElevation = 0.dp
                        ),
                    ) {
                        Text(text = "Sign Out", fontSize = 20.sp)
                        when (val response = authViewModel.signOutState.value) {
                            is Response.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                            }
                            is Response.Success -> {
                                if (response.data) {
                                    LaunchedEffect(key1 = true) {
                                        navController.navigate(route = Screens.LoginScreen.route) {
                                            popUpTo(Screens.ProfileScreen.route) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                }
                            }
                            is Response.Error -> {
                                Toast(message = response.message)
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = redScaffold
                )
            )
        },
        content = {

            when (val response = userViewModel.getUserData.value) {
                is Response.Loading -> {
                    CircularProgressIndicator()
                }
                is Response.Success -> {
                    if (response.data != null) {
                        val obj = response.data
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = it.calculateTopPadding())
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                top = 10.dp,
                                                start = 10.dp,
                                                bottom = 10.dp,
                                                end = 20.dp
                                            )
                                    ) {
                                        RoundedImage(
                                            image = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current)
                                                .data(obj.imageUrl)
                                                .build()),
                                            modifier = Modifier
                                                .size(150.dp)
                                                .weight(3.5f)
                                        )
                                    }
                                }
                                MyProfile(
                                    firstName = obj.firstName,
                                    lastName = obj.lastName,
                                    age = obj.age,
                                    email = obj.email,
                                    password = obj.password,
                                    navController = navController
                                )
                                Spacer(
                                    modifier = Modifier
                                        .height(20.dp)
                                )
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .padding(10.dp),
                                    shape = RoundedCornerShape(15.dp),
                                    elevation = CardDefaults.cardElevation(5.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .align(Alignment.CenterHorizontally),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Text(
                                            text = AnnotatedString("Export as CSV"),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold,
                                        )
                                        Spacer(modifier = Modifier.height(5.dp))
                                        ProfileExportFile()
                                    }
                                }
                            }
                        }
                    }
                }
                is Response.Error -> {
                    Toast(message = response.message)
                }
            }


        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.PROFILE, navController = navController)
        }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProfileExportFile() {
    val bloodPressureViewModel: BloodPressureReadingsViewModel = hiltViewModel()
    bloodPressureViewModel.getAllReadings()
    val heartRateViewModel: HeartRateReadingsViewModel = hiltViewModel()
    heartRateViewModel.getAllHeartReadings()

    when (val response = bloodPressureViewModel.bloodPressureReadingData.value) {
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {
            val obj = response.data
            bloodPressureReadingsCSV = obj
            when (val response = heartRateViewModel.heartRateReadingData.value) {
                is Response.Loading -> {
                    CircularProgressIndicator()
                }
                is Response.Success -> {
                    val obj = response.data
                    heartRateReadingsCSV = obj
                }
                is Response.Error -> {
                    Toast(message = response.message)
                }
            }
        }
        is Response.Error -> {
            Toast(message = response.message)
        }
    }

    Button(
        modifier = Modifier
            .fillMaxWidth(0.7f),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp,
            pressedElevation = 7.dp,
            disabledElevation = 0.dp
        ),
        onClick = {

                FileOutputStream("BloodPressure&HeartRateReadings.csv").apply {
                    exportToCSV(
                        bloodPressureReadingsCSV, heartRateReadingsCSV
                    )
                }
        }

    ) {
        Text(text = AnnotatedString("Export"), textAlign = TextAlign.Center, fontSize = 20.sp)
    }
}

fun OutputStream.exportToCSV(bloodPressureReadings: List<BloodPressureReadings>, heartRateReadings: List<HeartRateReadings>) {
    val writer = bufferedWriter()
    writer.write(""""Systolic Pressure", "Diastolic Pressure", "Timestamp"""")
    writer.newLine()
    bloodPressureReadings.forEach {
        writer.write("${it.systolicPressure}, ${it.diastolicPressure}, \"${it.timestamp}\"")
        writer.newLine()
    }
    writer.write(""""BPM", "Reading Status", "Timestamp"""")
    heartRateReadings.forEach {
        writer.write("${it.bpm}, ${it.readingStatus}, \"${it.timestamp}\"")
        writer.newLine()
    }
    writer.flush()
}