package com.example.bloodpressureapplication.presentation.profile

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.presentation.authentication.AuthenticationViewModel
import com.example.bloodpressureapplication.presentation.profile.components.ActionButton
import com.example.bloodpressureapplication.presentation.profile.components.MyProfile
import com.example.bloodpressureapplication.presentation.profile.components.RoundedImage
import com.example.bloodpressureapplication.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
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
                    Text(text = "Profile", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                },
                actions = {
                    Button(
                        onClick = {
                            authViewModel.signOut()
                        }
                    ) {
                        Text(text = "Sign Out")
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
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
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
                                    elevation = CardDefaults.cardElevation()
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

@Composable
fun ProfileExportFile() {

    Button(
        modifier = Modifier
            .fillMaxWidth(0.7f),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp,
            pressedElevation = 7.dp,
            disabledElevation = 0.dp
        ),
        onClick = { Log.d("ClickableButton", "Export button pressed")}
    ) {
        Text(text = AnnotatedString("Export"), textAlign = TextAlign.Center)
    }
}