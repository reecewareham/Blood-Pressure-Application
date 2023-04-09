package com.example.bloodpressureapplication.presentation.authentication

import com.example.bloodpressureapplication.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
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

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(10.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Red),

                )
            {
                Image(
                    painter = painterResource(id = R.drawable.bloodpressurelogo),
                    contentDescription = "Sign Up Screen Logo",
                    modifier = Modifier
                        .width(250.dp)
                        .padding(8.dp)
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(10.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {

                Text(
                    text = "Sign Up",
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally),
                    fontSize = 30.sp,
                    fontFamily = FontFamily.SansSerif
                )

                OutlinedTextField(
                    value = emailState.value, onValueChange = {
                        emailState.value = it
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally),
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
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally),
                    singleLine = true,
                    label = {
                        Text(text = "Enter your password: ")
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                OutlinedTextField(
                    value = firstNameState.value, onValueChange = {
                        firstNameState.value = it
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally),
                    singleLine = true,
                    label = {
                        Text(text = "Enter your first name: ")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                OutlinedTextField(
                    value = lastNameState.value, onValueChange = {
                        lastNameState.value = it
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally),
                    singleLine = true,
                    label = {
                        Text(text = "Enter your last name: ")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                OutlinedTextField(
                    value = ageState.value, onValueChange = {
                        ageState.value = it
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally),
                    singleLine = true,
                    label = {
                        Text(text = "Enter your age: ")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
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
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(15.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 7.dp,
                        disabledElevation = 0.dp
                    )
                ) {
                    Text(text = "Sign Up", fontSize = 20.sp)
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
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}