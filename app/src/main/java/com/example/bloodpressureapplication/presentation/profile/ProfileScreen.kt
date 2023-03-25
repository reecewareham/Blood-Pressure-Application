package com.example.bloodpressureapplication.presentation.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
                            .weight(1f)
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
                            age = obj.age
                        )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                        ) {
                            ActionButton(
                                text = "Edit Profile",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.45f)
                                    .height(35.dp)
                                    .clickable {

                                    }
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .height(15.dp)
                        )
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