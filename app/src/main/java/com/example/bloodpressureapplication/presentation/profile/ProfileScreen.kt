package com.example.bloodpressureapplication.presentation.profile

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.presentation.authentication.AuthenticationViewModel
import com.example.bloodpressureapplication.presentation.profile.components.ActionButton
import com.example.bloodpressureapplication.presentation.profile.components.MyProfile
import com.example.bloodpressureapplication.presentation.profile.components.RoundedImage
import com.example.bloodpressureapplication.util.Screens

@Composable
fun ProfileScreen(
    navController : NavController
) {
    val userViewModel: UserViewModel = hiltViewModel()
    val authViewModel: AuthenticationViewModel = hiltViewModel()
    userViewModel.getUserInfo()

    when (val response = userViewModel.getUserData.value) {
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {
            if (response.data != null) {
                val obj = response.data
                var selectedTabIndex by remember {
                    mutableStateOf(0)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Profile",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                )
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
                            backgroundColor = Color.White,
                            elevation = 10.dp
                        )
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
                                    image = rememberImagePainter(data = obj.imageUrl),
                                    modifier = Modifier
                                        .size(100.dp)
                                        .weight(3.5f)
                                )
                            }
                        }
                        MyProfile(
                            firstName = obj.firstName,
                            lastName = obj.lastName,
                            age = obj.age,
                            email = obj.email,
                            password = obj.password
                        )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(5.dp),
                            shape = RoundedCornerShape(15.dp),
                            elevation = 5.dp
                        ) {
                            Text(text = AnnotatedString("Export as CSV"), textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
                            Spacer(modifier = Modifier.height(10.dp))
                            ProfileExportFile()
                        }
                    }
                    BottomNavigationMenu(selectedItem = BottomNavigationItem.PROFILE, navController = navController)
                }
            }
        }
        is Response.Error -> {
            Toast(message = response.message)
        }
    }
}

@Composable
fun ProfileExportFile() {

    Button(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .padding(5.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 5.dp,
            pressedElevation = 7.dp,
            disabledElevation = 0.dp
        ),
        onClick = { Log.d("ClickableButton", "Export button pressed")}
    ) {
        Text(text = AnnotatedString("Export"), textAlign = TextAlign.Center)
    }
}