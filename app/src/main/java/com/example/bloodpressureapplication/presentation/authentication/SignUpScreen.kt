package com.example.bloodpressureapplication.presentation.authentication

import com.example.bloodpressureapplication.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens

@Composable
fun SignUpScreen(navController: NavHostController, viewModel: AuthenticationViewModel) {

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
            val firstNameState = remember {
                mutableStateOf("")
            }
            val lastNameState = remember {
                mutableStateOf("")
            }
            val ageState = remember {
                mutableStateOf("")
            }

            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "Sign Up Screen Logo",
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp)
            )

            Text(
                text = "Sign Up",
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
                label = {
                    Text(text = "Enter your email: ")
                }
            )

            OutlinedTextField(value = passwordState.value, onValueChange = {
                passwordState.value = it
            },
                modifier = Modifier
                    .padding(10.dp),
                label = {
                    Text(text = "Enter your password: ")
                },
                visualTransformation = PasswordVisualTransformation()
            )

            OutlinedTextField(value = firstNameState.value, onValueChange = {
                firstNameState.value = it
            },
                modifier = Modifier
                    .padding(10.dp),
                label = {
                    Text(text = "Enter your first name: ")
                }
            )

            OutlinedTextField(value = lastNameState.value, onValueChange = {
                lastNameState.value = it
            },
                modifier = Modifier
                    .padding(10.dp),
                label = {
                    Text(text = "Enter your last name: ")
                }
            )

            OutlinedTextField(value = ageState.value, onValueChange = {
                ageState.value = it
            },
                modifier = Modifier
                    .padding(10.dp),
                label = {
                    Text(text = "Enter your age: ")
                }
            )

            Button(
                onClick = {
                    viewModel.signUp(
                        email = emailState.value,
                        password = passwordState.value,
                        firstName = firstNameState.value,
                        lastName = lastNameState.value,
                        age = ageState.value,
                        imageUrl = ""
                    )
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = "Sign Up")
                when (val response = viewModel.signUpState.value) {
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
                            Toast(message = "Sign up failed")
                        }
                    }
                    is Response.Error -> {
                        Toast(message = response.message)
                    }
                }
            }

            Text(
                text = "Already have an account? Login here.",
                color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(route = Screens.LoginScreen.route) {
                            launchSingleTop = true
                        }
                    }
            )
        }
    }
}