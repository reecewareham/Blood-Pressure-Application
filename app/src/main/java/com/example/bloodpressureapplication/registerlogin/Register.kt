package com.example.bloodpressureapplication.registerlogin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun RegisterScreen(navController: NavHostController) {

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val firstName = remember { mutableStateOf(TextFieldValue()) }
        val lastName = remember { mutableStateOf(TextFieldValue()) }
        val dob = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Register")

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "First Name") },
            value = firstName.value,
            onValueChange = {firstName.value = it}
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Last Name") },
            value = lastName.value,
            onValueChange = {lastName.value = it}
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Date of Birth") },
            value = dob.value,
            onValueChange = {dob.value = it}
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = {username.value = it}
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {password.value = it}
        )

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier.padding(20.dp)
        ) {
            Button(
                onClick = { navController.navigate(LoginRegisterRoutes.Login.route) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Register")
            }
        }
    }
}