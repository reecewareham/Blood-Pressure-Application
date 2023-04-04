package com.example.bloodpressureapplication.presentation.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bloodpressureapplication.util.Screens
import com.example.bloodpressureapplication.R
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.util.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, viewModel : AuthenticationViewModel) {

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
                    mutableStateOf("")
                }
                val passwordState = remember {
                    mutableStateOf("")
                }

                Image(
                    painter = painterResource(id = R.drawable.placeholder_image),
                    contentDescription = "Login Screen Logo",
                    modifier = Modifier
                        .width(250.dp)
                        .padding(top = 16.dp)
                        .padding(8.dp)
                )

                Text(
                    text = "Sign In",
                    modifier = Modifier
                        .padding(10.dp),
                    fontSize = 30.sp,
                    fontFamily = FontFamily.SansSerif
                )

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

                Button(
                    onClick = {
                        viewModel.signIn(email = emailState.value, password = passwordState.value)
                    },
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(text = "Sign In")
                    when (val response = viewModel.signInState.value) {
                        is Response.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                        is Response.Success -> {
                            if (response.data) {
                                LaunchedEffect(key1 = true) {
                                    navController.navigate(Screens.HomeScreen.route) {
                                        popUpTo(Screens.LoginScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            } else {
                                Toast(message = "Sign in failed")
                            }
                        }
                        is Response.Error -> {
                            Toast(message = response.message)
                        }
                    }
                }

                Text(
                    text = "New user? Sign up here.",
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(route = Screens.SignUpScreen.route) {
                                launchSingleTop = true
                            }
                        }
                )
            }
        }
}