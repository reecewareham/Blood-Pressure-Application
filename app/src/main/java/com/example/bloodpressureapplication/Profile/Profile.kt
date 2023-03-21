package com.example.bloodpressureapplication

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bloodpressureapplication.model.User

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    data: List<User>,
    firstName: String,
    lastName: String,
    email: String,
    password: String,
    DOB: String
) {

    Scaffold(
        content = {
            ProfileContent(
                data = data,
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password,
                DOB = DOB
            )
        }
    )
}

@Composable
fun ProfileContent(
    data: List<User>,
    firstName: String,
    lastName: String,
    email: String,
    password: String,
    DOB: String
) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfilePictureBox(painter = painterResource(id = R.drawable.cat))
        LazyColumn() {
            items(items = data, key = { it._id.toHexString() }) {
                ProfileDetailsBox(title = "First Name", userDetails = it.firstName)
                ProfileDetailsBox(title = "Last Name", userDetails = it.lastName)
                ProfileDetailsBox(title = "Email", userDetails = it.email)
                ProfileDetailsBox(title = "Password", userDetails = it.password)
                ProfileDetailsBox(title = "D.O.B.", userDetails = it.DOB)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(5.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Text(text = AnnotatedString("Export as CSV"), textAlign = TextAlign.Center, modifier = Modifier.padding(10.dp))
        }
        Spacer(modifier = Modifier.height(5.dp))
        ProfileExportFile()
    }
}

@Composable
fun ProfileDetailsBox(
    title: String,
    userDetails: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .padding(5.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title)
            Text(text = userDetails)
            ClickableText(
                text = AnnotatedString("Edit"),
                onClick = { Log.d("ClickableText", "Edit text clicked.") },
                style = TextStyle(color = Color.Blue)
            )
        }
    }
}

@Composable
fun ProfilePictureBox(
    painter: Painter,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .padding(5.dp),
        shape = CircleShape,
        elevation = 5.dp
    ) {

        Image(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            painter = painter,
            contentDescription = "Profile Picture"
        )

    }
}

@Composable
fun ProfileExportFile() {

    Button(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .padding(5.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 5.dp,
            pressedElevation = 7.dp,
            disabledElevation = 0.dp
        ),
        onClick = { Log.d("ClickableButton", "Export button pressed")}
    ) {
        Text(text = AnnotatedString("Export"), textAlign = TextAlign.Center)
    }
}