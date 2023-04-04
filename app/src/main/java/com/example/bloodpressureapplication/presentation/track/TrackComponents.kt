package com.example.bloodpressureapplication.presentation.track

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloodpressureapplication.domain.model.BloodPressureReadings
import com.example.bloodpressureapplication.domain.model.HeartRateReadings
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import java.util.*

fun checkReading(systolic: Int, diastolic: Int): Color {
    val colour : Color
    if (systolic < 90 || diastolic < 60) {
        //Hypotension
        colour = Color(0xFF3aeaf0)
    } else if ((systolic in 90..119) && (diastolic in 60..79)) {
        //Normal
        colour = Color.Green
    } else if ((systolic in 120..129) && diastolic < 80) {
        //Elevated
        colour = Color.Yellow
    } else if ((systolic in 130..139) || (diastolic in 80..89)) {
        //High Blood Pressure (Hypertension Stage 1)
        colour = Color(0xFFFFB52E)
    } else if ((systolic in 140..179) || (diastolic in 90..119)) {
        //High Blood Pressure (Hypertension Stage 2)
        colour = Color.Red
    } else {
        //Hypertensive Crisis (Consult Doctor)
        colour = Color(0xFF8c0f0f)
    }
    return colour
}

fun checkReadingText(systolic: Int, diastolic: Int): String {
    val text : String
    if (systolic < 90 || diastolic < 60) {
        //Hypotension
        text = "Your last blood pressure reading is too low and shows Hypotension. Consult the info page on ways to raise it."
    } else if ((systolic in 90..119) && (diastolic in 60..79)) {
        //Normal
        text = "Your last blood pressure reading is normal and at a healthy state. Keep it up!"
    } else if ((systolic in 120..129) && diastolic < 80) {
        //Elevated
        text = "Your last blood pressure reading is partially too high and shows an elevated blood pressure. Consult the info page on ways to lower it."
    } else if ((systolic in 130..139) || (diastolic in 80..89)) {
        //High Blood Pressure (Hypertension Stage 1)
        text = "Your last blood pressure reading is too high and shows stage 1 Hypertension. Consult the info page on ways to lower it and if it continues, consult a medical professional."
    } else if ((systolic in 140..179) || (diastolic in 90..119)) {
        //High Blood Pressure (Hypertension Stage 2)
        text = "Your last blood pressure reading is too high and shows stage 2 Hypertension. Consult the info page on ways to lower it and if it continues, consult a medical professional."
    } else {
        //Hypertensive Crisis (Consult Doctor)
        text = "Your last blood pressure reading is extremely high and shows a Hypertensive Crisis. Consult a medical professional immediately!"
    }
    return text
}



@Composable
fun BloodTrackContent(bloodPressureReadings: List<BloodPressureReadings>) {
    LazyColumn(
    ) {
        items(items = bloodPressureReadings, itemContent =  {
            BloodListContent(it)
            bloodPressureGraph = true
        })
    }

    if (bloodPressureGraph) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            fun getSys() = List(5) { FloatEntry(it.toFloat(), systolic5Values.elementAt(it)) }
            fun getDia() = List(5) { FloatEntry(it.toFloat(), diastolic5Values.elementAt(it)) }
            bloodChartEntryModel = ChartEntryModelProducer(getSys(), getDia())

        }
    }
}

@Composable
fun BloodListContent(it: BloodPressureReadings) {
    val sys = it.systolicPressure.toFloat()
    val dia = it.diastolicPressure.toFloat()

    systolic5Values.add(0,sys)
    diastolic5Values.add(0,dia)

    val test = Calendar.getInstance()
    test.time = it.timestamp?.toDate()
    val date = (test.get(Calendar.DAY_OF_MONTH).toString()) + "/" + ((test.get(Calendar.MONTH) + 1).toString())

    date5Values.add(0,date)

}

@Composable
fun BloodListOfReadingsBar(it: BloodPressureReadings) {

    val test = Calendar.getInstance()
    test.time = it.timestamp?.toDate()
    var date = (test.get(Calendar.DAY_OF_MONTH).toString()) + "/" + ((test.get(Calendar.MONTH) + 1).toString())

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.15f),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = "$date",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Spacer(
            modifier = Modifier
                .padding(3.dp)
        )
        Canvas(
            modifier = Modifier
                .size(width = 20.dp, height = 50.dp)
        ) {
            drawRoundRect(
                color = checkReading(it.systolicPressure, it.diastolicPressure),
                size = Size(width = 20.dp.toPx(), height = 50.dp.toPx()),
                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
            )
        }
        Spacer(
            modifier = Modifier
                .padding(5.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.4f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sys: " + it.systolicPressure.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Dia: " + it.diastolicPressure.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun BloodListOfReadings(bloodPressureReadings: List<BloodPressureReadings>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight(0.85f)
    ) {
        items(items = bloodPressureReadings, itemContent =  {
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            ) {
                BloodListOfReadingsBar(it)
            }
        })
    }
}

@Composable
fun HeartTrackContent(heartRateReadings: List<HeartRateReadings>) {
    LazyColumn(
    ) {
        items(items = heartRateReadings, itemContent =  {
            HeartListContent(it)
            heartRateGraph = true
        })
    }

    if (heartRateGraph) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            fun getBpm() = List(5) { FloatEntry(it.toFloat(), bpm5Values.elementAt(it)) }
            heartChartEntryModel = ChartEntryModelProducer(getBpm())

        }
    }
}

@Composable
fun HeartListContent(it: HeartRateReadings) {
    val bpm = it.bpm.toFloat()
    val status = it.readingStatus

    bpm5Values.add(0,bpm)
    status5Values.add(0,status)

    val test = Calendar.getInstance()
    test.time = it.timestamp?.toDate()
    val date = (test.get(Calendar.DAY_OF_MONTH).toString()) + "/" + ((test.get(Calendar.MONTH) + 1).toString())

    date5HeartValues.add(0,date)

}

@Composable
fun HeartListOfReadingsBar(it: HeartRateReadings) {

    val test = Calendar.getInstance()
    test.time = it.timestamp?.toDate()
    var date = (test.get(Calendar.DAY_OF_MONTH).toString()) + "/" + ((test.get(Calendar.MONTH) + 1).toString())

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.15f),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = "$date",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Spacer(
            modifier = Modifier
                .padding(3.dp)
        )
        Canvas(
            modifier = Modifier
                .size(width = 20.dp, height = 50.dp)
        ) {
            drawRoundRect(
                color = Color.Red,
                size = Size(width = 20.dp.toPx(), height = 50.dp.toPx()),
                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
            )
        }
        Spacer(
            modifier = Modifier
                .padding(5.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.4f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "BPM: " + it.bpm.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Status: " + it.readingStatus,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun HeartListOfReadings(heartRateReadings: List<HeartRateReadings>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight(0.85f)
    ) {
        items(items = heartRateReadings, itemContent =  {
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            ) {
                HeartListOfReadingsBar(it)
            }
        })
    }
}