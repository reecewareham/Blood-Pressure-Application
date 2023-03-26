package com.example.bloodpressureapplication.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyProfile(
    firstName: String,
    lastName: String,
    age: String,
    email: String,
    password: String
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
            Text(text = "Password: " + password, fontWeight = FontWeight.Bold, lineHeight = 20.sp)
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            ActionButton(
                text = "Edit Profile",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.45f)
                    .height(35.dp)
                    .clickable {

                    }
                )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
        }
    }
}