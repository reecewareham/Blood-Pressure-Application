package com.example.bloodpressureapplication.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bloodpressureapplication.presentation.Toast
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.Screens

@Composable
fun MyProfile(
    firstName: String,
    lastName: String,
    age: String,
    email: String,
    password: String,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(text = "First Name: " + firstName, fontWeight = FontWeight.Bold, lineHeight = 20.sp)
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(text = "Last Name: " + lastName, fontWeight = FontWeight.Bold, lineHeight = 20.sp)
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(text = "Age: " + age, fontWeight = FontWeight.Bold, lineHeight = 20.sp)
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(text = "Email: " + email, fontWeight = FontWeight.Bold, lineHeight = 20.sp)
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            var hiddenPassword = ""
            for(letter in password) {
                hiddenPassword += "*"
            }
            Text(text = "Password: " + hiddenPassword, fontWeight = FontWeight.Bold, lineHeight = 20.sp)
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                shape = RoundedCornerShape(15.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 7.dp,
                    disabledElevation = 0.dp
                ),
                onClick = { navController.navigate(Screens.ProfileEditScreen.route) }
            ) {
                Text(text = AnnotatedString("Edit Profile"), textAlign = TextAlign.Center)
            }

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
        }
    }
}