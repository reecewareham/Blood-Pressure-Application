package com.example.bloodpressureapplication.presentation.track

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bloodpressureapplication.domain.model.BloodPressureReadings
import com.example.bloodpressureapplication.domain.model.HeartRateReadings
import com.example.bloodpressureapplication.ui.theme.red
import com.example.bloodpressureapplication.util.Screens
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
    } else if ((systolic in 120..129) && (diastolic in 60..79)) {
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
    } else if ((systolic in 120..129) && (diastolic in 60..79)) {
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

fun checkHeartReading(bpm: Int, status: String, userAge: Int) : Color {

    var colour = Color.White

    if (status == "Resting") {
        if (bpm < 60) {
            //Slow heart rate (bradycardia)
            colour = Color(0xFFFFB52E)
        } else if (bpm in 60..100) {
            //Normal heart rate
            colour = Color.Green
        } else if (bpm > 100) {
            //Fast heart rate (tachycardia)
            colour = Color.Red
        }
    } else if (status == "Active" || status == "Exercise") {
        if (userAge in 0..30) {
            if (bpm < 100) {
                //Too slow for active
                colour = Color(0xFFFFB52E)
            } else if (bpm in 100..140) {
                //Good Range
                colour = Color.Green
            } else if (bpm > 140) {
                //Too high
                colour = Color.Red
            }
        } else if (userAge in 30..40) {
            if (bpm < 95) {
                //Too slow for active
                colour = Color(0xFFFFB52E)
            } else if (bpm in 95..133) {
                //Good Range
                colour = Color.Green
            } else if (bpm > 133) {
                //Too high
                colour = Color.Red
            }
        } else if (userAge in 40..50) {
            if (bpm < 90) {
                //Too slow for active
                colour = Color(0xFFFFB52E)
            } else if (bpm in 90..126) {
                //Good Range
                colour = Color.Green
            } else if (bpm > 126) {
                //Too high
                colour = Color.Red
            }
        } else if (userAge in 50..60) {
            if (bpm < 85) {
                //Too slow for active
                colour = Color(0xFFFFB52E)
            } else if (bpm in 85..119) {
                //Good Range
                colour = Color.Green
            } else if (bpm > 119) {
                //Too high
                colour = Color.Red
            }
        } else if (userAge in 60..70) {
            if (bpm < 80) {
                //Too slow for active
                colour = Color(0xFFFFB52E)
            } else if (bpm in 80..112) {
                //Good Range
                colour = Color.Green
            } else if (bpm > 112) {
                //Too high
                colour = Color.Red
            }
        } else if (userAge in 70..80) {
            if (bpm < 75) {
                //Too slow for active
                colour = Color(0xFFFFB52E)
            } else if (bpm in 75..105) {
                //Good Range
                colour = Color.Green
            } else if (bpm > 105) {
                //Too high
                colour = Color.Red
            }
        } else if (userAge in 80..90) {
            if (bpm < 70) {
                //Too slow for active
                colour = Color(0xFFFFB52E)
            } else if (bpm in 70..98) {
                //Good Range
                colour = Color.Green
            } else if (bpm > 98) {
                //Too high
                colour = Color.Red
            }
        } else if (userAge > 90) {
            if (bpm < 65) {
                //Too slow for active
                colour = Color(0xFFFFB52E)
            } else if (bpm in 65..91) {
                //Good Range
                colour = Color.Green
            } else if (bpm > 91) {
                //Too high
                colour = Color.Red
            }
        }
    }
    return colour
}

fun checkHeartReadingText(bpm: Int, status: String, userAge: Int) : String {

    var text = ""
    if (status == "Resting") {
        if (bpm < 60) {
            //Slow heart rate (bradycardia)
            text = "Your heart rate is showing bradycardia because it is too low for resting. If this is a common occurrence, please consult a doctor."
        } else if (bpm in 60..100) {
            //Normal heart rate
            text = "Your heart rate is within the healthy resting range. Keep it up."
        } else if (bpm > 100) {
            //Fast heart rate (tachycardia)
            text = "Your heart rate is showing tachycardia because it is too high for resting. If this is a common occurrence, please consult a doctor."
        }
    } else if (status == "Active" || status == "Exercise") {
        if (userAge in 0..30) {
            if (bpm < 100) {
                //Too slow for active
                text = "Your heart rate is showing bradycardia because it is too low for being active. If this is a common occurrence, please consult a doctor."
            } else if (bpm in 100..140) {
                //Good Range
                text = "Your heart rate is within the healthy active range. Keep it up."
            } else if (bpm > 140) {
                //Too high
                text = "Your heart rate is showing tachycardia because it is too high for being active. If this is a common occurrence, please consult a doctor."
            }
        } else if (userAge in 30..40) {
            if (bpm < 95) {
                //Too slow for active
                text = "Your heart rate is showing bradycardia because it is too low for being active. If this is a common occurrence, please consult a doctor."
            } else if (bpm in 95..133) {
                //Good Range
                text = "Your heart rate is within the healthy active range. Keep it up."
            } else if (bpm > 133) {
                //Too high
                text = "Your heart rate is showing tachycardia because it is too high for being active. If this is a common occurrence, please consult a doctor."
            }
        } else if (userAge in 40..50) {
            if (bpm < 90) {
                //Too slow for active
                text = "Your heart rate is showing bradycardia because it is too low for being active. If this is a common occurrence, please consult a doctor."
            } else if (bpm in 90..126) {
                //Good Range
                text = "Your heart rate is within the healthy active range. Keep it up."
            } else if (bpm > 126) {
                //Too high
                text = "Your heart rate is showing tachycardia because it is too high for being active. If this is a common occurrence, please consult a doctor."
            }
        } else if (userAge in 50..60) {
            if (bpm < 85) {
                //Too slow for active
                text = "Your heart rate is showing bradycardia because it is too low for being active. If this is a common occurrence, please consult a doctor."
            } else if (bpm in 85..119) {
                //Good Range
                text = "Your heart rate is within the healthy active range. Keep it up."
            } else if (bpm > 119) {
                //Too high
                text = "Your heart rate is showing tachycardia because it is too high for being active. If this is a common occurrence, please consult a doctor."
            }
        } else if (userAge in 60..70) {
            if (bpm < 80) {
                //Too slow for active
                text = "Your heart rate is showing bradycardia because it is too low for being active. If this is a common occurrence, please consult a doctor."
            } else if (bpm in 80..112) {
                //Good Range
                text = "Your heart rate is within the healthy active range. Keep it up."
            } else if (bpm > 112) {
                //Too high
                text = "Your heart rate is showing tachycardia because it is too high for being active. If this is a common occurrence, please consult a doctor."
            }
        } else if (userAge in 70..80) {
            if (bpm < 75) {
                //Too slow for active
                text = "Your heart rate is showing bradycardia because it is too low for being active. If this is a common occurrence, please consult a doctor."
            } else if (bpm in 75..105) {
                //Good Range
                text = "Your heart rate is within the healthy active range. Keep it up."
            } else if (bpm > 105) {
                //Too high
                text = "Your heart rate is showing tachycardia because it is too high for being active. If this is a common occurrence, please consult a doctor."
            }
        } else if (userAge in 80..90) {
            if (bpm < 70) {
                //Too slow for active
                text = "Your heart rate is showing bradycardia because it is too low for being active. If this is a common occurrence, please consult a doctor."
            } else if (bpm in 70..98) {
                //Good Range
                text = "Your heart rate is within the healthy active range. Keep it up."
            } else if (bpm > 98) {
                //Too high
                text = "Your heart rate is showing tachycardia because it is too high for being active. If this is a common occurrence, please consult a doctor."
            }
        } else if (userAge > 90) {
            if (bpm < 65) {
                //Too slow for active
                text = "Your heart rate is showing bradycardia because it is too low for being active. If this is a common occurrence, please consult a doctor."
            } else if (bpm in 65..91) {
                //Good Range
                text = "Your heart rate is within the healthy active range. Keep it up."
            } else if (bpm > 91){
                //Too high
                text = "Your heart rate is showing tachycardia because it is too high for being active. If this is a common occurrence, please consult a doctor."
            }
        }
    }
    return text
}



fun bloodTrackContent(bloodPressureReadings: List<BloodPressureReadings>) {
    systolicValues.clear()
    diastolicValues.clear()
    dateValues.clear()

    for (item in bloodPressureReadings) {
        val sys = item.systolicPressure.toFloat()
        val dia = item.diastolicPressure.toFloat()

        systolicValues.add(0,sys)
        diastolicValues.add(0,dia)

        val test = Calendar.getInstance()
        test.time = item.timestamp?.toDate()!!
        val date = (test.get(Calendar.DAY_OF_MONTH).toString()) + "/" + ((test.get(Calendar.MONTH) + 1).toString())

        dateValues.add(0,date)
    }

    systolicTemp = ArrayList(systolicValues.reversed())
    diastolicTemp = ArrayList(diastolicValues.reversed())
    dateTemp = ArrayList(dateValues.reversed())

    fun getSys() = List(systolicTemp.size) { FloatEntry(it.toFloat(), systolicTemp.elementAt(it)) }
    fun getDia() = List(diastolicTemp.size) { FloatEntry(it.toFloat(), diastolicTemp.elementAt(it)) }
    bloodChartEntryModel = ChartEntryModelProducer(getSys(), getDia())
}

@Composable
fun BloodListOfReadingsBar(it: BloodPressureReadings, navController: NavController) {

    val test = Calendar.getInstance()
    test.time = it.timestamp?.toDate()!!
    val date = (test.get(Calendar.DAY_OF_MONTH).toString()) + "/" + ((test.get(Calendar.MONTH) + 1).toString())

    val colour = checkReading(it.systolicPressure, it.diastolicPressure)
    var text = ""

    if (colour == Color(0xFF3aeaf0)) {
        text = "Hypotension"
    } else if (colour == Color.Green) {
        text = "Normal"
    } else if (colour == Color.Yellow) {
        text = "Elevated"
    } else if(colour == Color(0xFFFFB52E)) {
        text = "Hypertension Stage 1"
    } else if (colour == Color.Red) {
        text = "Hypertension Stage 2"
    } else if (colour == Color(0xFF8c0f0f)) {
        text = "Hypertensive Crisis"
    }

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
                text = date,
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
                color = colour,
                size = Size(width = 20.dp.toPx(), height = 50.dp.toPx()),
                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
            )
        }
        Spacer(
            modifier = Modifier
                .padding(5.dp)
        )
        Column(
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            Row() {
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {
                        bloodReadingEdit = it.bloodPressureReadingId
                        sysEdit = it.systolicPressure
                        diaEdit = it.diastolicPressure
                        navController.navigate(Screens.EditBloodPressureScreen.route)

                    }) {
                        Icon(Icons.Filled.Edit, "EditIcon", tint = red)
                    }
                }
            }
        }
    }
}

@Composable
fun BloodListOfReadings(bloodPressureReadings: List<BloodPressureReadings>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight(0.85f)
    ) {
        items(items = bloodPressureReadings, itemContent =  {
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                BloodListOfReadingsBar(it, navController)
            }
        })
    }
}

fun heartTrackContent(heartRateReadings: List<HeartRateReadings>) {
    bpmValues.clear()
    statusValues.clear()
    dateHeartValues.clear()

    for(item in heartRateReadings) {
        val bpm = item.bpm.toFloat()
        val status = item.readingStatus

        bpmValues.add(0,bpm)
        statusValues.add(0,status)

        val test = Calendar.getInstance()
        test.time = item.timestamp?.toDate()!!
        val date = (test.get(Calendar.DAY_OF_MONTH).toString()) + "/" + ((test.get(Calendar.MONTH) + 1).toString())

        dateHeartValues.add(0,date)
    }

    bpmTemp = ArrayList(bpmValues.reversed())
    statusTemp = ArrayList(statusValues.reversed())
    dateHeartTemp = ArrayList(dateHeartValues.reversed())

    fun getBpm() = List(bpmTemp.size) { FloatEntry(it.toFloat(), bpmTemp.elementAt(it)) }
    heartChartEntryModel = ChartEntryModelProducer(getBpm())
}

@Composable
fun HeartListOfReadingsBar(it: HeartRateReadings, navController: NavController) {

    val test = Calendar.getInstance()
    test.time = it.timestamp?.toDate()!!
    val date = (test.get(Calendar.DAY_OF_MONTH).toString()) + "/" + ((test.get(Calendar.MONTH) + 1).toString())

    val colour = checkHeartReading(it.bpm, it.readingStatus, userAge)
    var text = ""

    if (colour == Color(0xFFFFB52E)) {
        text = "Bradycardia"
    } else if (colour == Color.Green) {
        text = "Normal"
    } else if (colour == Color.Red) {
        text = "Tachycardia"
    }

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
                text = date,
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
                color = colour,
                size = Size(width = 20.dp.toPx(), height = 50.dp.toPx()),
                cornerRadius = CornerRadius(x = 20.dp.toPx(), y = 20.dp.toPx())
            )
        }
        Spacer(
            modifier = Modifier
                .padding(5.dp)
        )
        Column(
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            Row() {

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
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {
                        heartReadingEdit = it.heartRateReadingId
                        bpmEdit = it.bpm
                        statusEdit = it.readingStatus
                        navController.navigate(Screens.EditHeartRateScreen.route)

                    }) {
                        Icon(Icons.Filled.Edit, "EditIcon", tint = red)
                    }
                }
            }
        }
    }
}

@Composable
fun HeartListOfReadings(heartRateReadings: List<HeartRateReadings>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight(0.85f)
    ) {
        items(items = heartRateReadings, itemContent =  {
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                HeartListOfReadingsBar(it, navController)
            }
        })
    }
}