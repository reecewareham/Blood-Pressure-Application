package com.example.bloodpressureapplication.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Text(
                        text = "First Name: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Text(
                        text = firstName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Text(
                        text = "Last Name: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Text(
                        text = lastName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Text(
                        text = "Age: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Text(
                        text = age,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Text(
                        text = "Email: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Text(
                        text = email,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            var hiddenPassword = ""
            for(letter in password) {
                hiddenPassword += "*"
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Text(
                        text = "Password: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Text(
                        text = hiddenPassword,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                shape = RoundedCornerShape(15.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 7.dp,
                    disabledElevation = 0.dp
                ),
                onClick = { navController.navigate(Screens.ProfileEditScreen.route) }
            ) {
                Text(text = AnnotatedString("Edit Profile"), textAlign = TextAlign.Center, fontSize = 20.sp)
            }

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
        }
    }
}