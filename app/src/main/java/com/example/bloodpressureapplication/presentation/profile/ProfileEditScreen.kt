package com.example.bloodpressureapplication.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.bloodpressureapplication.presentation.BottomNavigationItem
import com.example.bloodpressureapplication.presentation.BottomNavigationMenu
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.presentation.authentication.AuthenticationViewModel
import com.example.bloodpressureapplication.presentation.profile.components.MyProfile
import com.example.bloodpressureapplication.presentation.profile.components.RoundedImage
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileEditScreen(
    navController: NavHostController
) {

    val userViewModel: UserViewModel = hiltViewModel()
    userViewModel.getUserInfo()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Edit Profile", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screens.ProfileScreen.route)

                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                actions = {

                },
                backgroundColor = Color.White,
                elevation = 10.dp
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

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .verticalScroll(rememberScrollState()),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val emailState = remember {
                                    mutableStateOf(obj.email)
                                }
                                val passwordState = remember {
                                    mutableStateOf(obj.password)
                                }
                                val firstNameState = remember {
                                    mutableStateOf(obj.firstName)
                                }
                                val lastNameState = remember {
                                    mutableStateOf(obj.lastName)
                                }
                                val ageState = remember {
                                    mutableStateOf(obj.age)
                                }
                                val imageURLState = remember {
                                    mutableStateOf(obj.imageUrl)
                                }


                                OutlinedTextField(value = emailState.value, onValueChange = {
                                    emailState.value = it
                                },
                                    modifier = Modifier
                                        .padding(10.dp),
                                    singleLine = true,
                                    label = {
                                        Text(text = "Enter your email: ")
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                                )

                                OutlinedTextField(
                                    value = passwordState.value, onValueChange = {
                                        passwordState.value = it
                                    },
                                    modifier = Modifier
                                        .padding(10.dp),
                                    singleLine = true,
                                    label = {
                                        Text(text = "Enter your password: ")
                                    },
                                    visualTransformation = PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                                )

                                OutlinedTextField(value = firstNameState.value, onValueChange = {
                                    firstNameState.value = it
                                },
                                    modifier = Modifier
                                        .padding(10.dp),
                                    singleLine = true,
                                    label = {
                                        Text(text = "Enter your first name: ")
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                                )

                                OutlinedTextField(value = lastNameState.value, onValueChange = {
                                    lastNameState.value = it
                                },
                                    modifier = Modifier
                                        .padding(10.dp),
                                    singleLine = true,
                                    label = {
                                        Text(text = "Enter your last name: ")
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                                )

                                OutlinedTextField(value = ageState.value, onValueChange = {
                                    ageState.value = it
                                },
                                    modifier = Modifier
                                        .padding(10.dp),
                                    singleLine = true,
                                    label = {
                                        Text(text = "Enter your age: ")
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                )

                                OutlinedTextField(value = imageURLState.value, onValueChange = {
                                    imageURLState.value = it
                                },
                                    modifier = Modifier
                                        .padding(10.dp),
                                    singleLine = true,
                                    label = {
                                        Text(text = "Enter your image url: ")
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                                )

                                Button(
                                    onClick = {
                                        userViewModel.setUserInfo(
                                            email = emailState.value,
                                            password = passwordState.value,
                                            firstName = firstNameState.value,
                                            lastName = lastNameState.value,
                                            age = ageState.value,
                                            imageUrl = imageURLState.value
                                        )
                                    },
                                    modifier = Modifier
                                        .padding(8.dp)
                                ) {
                                    Text(text = "Edit Profile")

                                    when (val response = userViewModel.setUserData.value) {
                                        is Response.Loading -> {
                                            CircularProgressIndicator(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                            )
                                        }
                                        is Response.Success -> {
                                            if (response.data) {
                                                LaunchedEffect(key1 = true) {
                                                    navController.navigate(Screens.ProfileScreen.route) {
                                                        popUpTo(Screens.ProfileEditScreen.route) {
                                                            inclusive = true
                                                        }
                                                    }
                                                }
                                            } else {
                                                Toast(message = "Edit failed")
                                            }
                                        }
                                        is Response.Error -> {
                                            Toast(message = response.message)
                                        }
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
