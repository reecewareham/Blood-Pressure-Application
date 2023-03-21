package com.example.bloodpressureapplication.Info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import com.example.bloodpressureapplication.R


@Composable
fun InfoScreen() {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Info Screen")
        var title = "Understanding\nBlood Pressure"
        InfoBox(title = title, painter = painterResource(id = R.drawable.cat), contentDescription = title)
        title = "Understanding\nHypotension"
        InfoBox(title = title, painter = painterResource(id = R.drawable.cat), contentDescription = title)
        title = "Understanding\nHypertension"
        InfoBox(title = title, painter = painterResource(id = R.drawable.cat), contentDescription = title)
    }
}

@Composable
fun InfoBox(
    title: String,
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(150.dp)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, textAlign = TextAlign.Center)
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
        }
    }
}