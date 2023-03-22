package com.example.bloodpressureapplication.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel? = null,
    onNavToHomePage:() -> Unit,
    onNavToSignUpPage:() -> Unit,
) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary
        )

        if (isError) {
            Text(text = loginUiState?.loginError ?: "Unknown Error", color = Color.Red)
        }
        
        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = loginUiState?.email ?: "",
            onValueChange = {loginViewModel?.onEmailChange(it)},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Email"
                )
            },
            isError = isError
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = loginUiState?.password ?: "",
            onValueChange = {loginViewModel?.onPasswordChange(it)},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Password"
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )

        Button(
            onClick = {loginViewModel?.loginUser(context)}
        ) {
            Text(text = "Sign In")
        }

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = "Don't have an account?")
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(onClick = { onNavToSignUpPage.invoke() }) {
                Text(text = "Sign Up")
            }
        }

        if (loginUiState?.isLoading == true) {
            CircularProgressIndicator()
        }

        LaunchedEffect(key1 = loginViewModel?.hasUser) {
            if (loginViewModel?.hasUser == true) {
                onNavToHomePage.invoke()
            }
        }

    }
}

@Composable
fun SignUpScreen(
    loginViewModel: LoginViewModel? = null,
    onNavToHomePage:() -> Unit,
    onNavToLoginPage:() -> Unit,
) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.signUpError != null
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary
        )

        if (isError) {
            Text(text = loginUiState?.signUpError ?: "Unknown Error", color = Color.Red)
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = loginUiState?.emailSignUp ?: "",
            onValueChange = {loginViewModel?.onEmailChangeSignUp(it)},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Email"
                )
            },
            isError = isError
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = loginUiState?.passwordSignUp ?: "",
            onValueChange = {loginViewModel?.onPasswordChangeSignUp(it)},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Password"
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = loginUiState?.confirmPasswordSignUp ?: "",
            onValueChange = {loginViewModel?.onConfirmPasswordChange(it)},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Confirm Password"
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError
        )

        Button(
            onClick = {loginViewModel?.createUser(context)}
        ) {
            Text(text = "Sign Up")
        }

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = "Already have an Account?")
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(onClick = { onNavToLoginPage.invoke() }) {
                Text(text = "Sign In")
            }
        }

        if (loginUiState?.isLoading == true) {
            CircularProgressIndicator()
        }

        LaunchedEffect(key1 = loginViewModel?.hasUser) {
            if (loginViewModel?.hasUser == true) {
                onNavToHomePage.invoke()
            }
        }

    }
}