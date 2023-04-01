package com.example.bloodpressureapplication.presentation.track

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bloodpressureapplication.R
import com.example.bloodpressureapplication.domain.model.BloodPressureReadings
import com.example.bloodpressureapplication.domain.model.HeartRateReadings
import com.example.bloodpressureapplication.presentation.*
import com.example.bloodpressureapplication.util.Response
import com.example.bloodpressureapplication.util.rememberChartStyle
import com.example.bloodpressureapplication.util.rememberMarker
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.legend.verticalLegend
import com.patrykandpatrick.vico.compose.legend.verticalLegendItem
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.column.ColumnChart
import com.patrykandpatrick.vico.core.chart.decoration.ThresholdLine
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.entry.*
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.Calendar

var systolic5Values = arrayListOf<Float>()
var diastolic5Values = arrayListOf<Float>()
var date5Values = arrayListOf<String>()
var bloodPressureGraph = false

var bpmValues = arrayListOf<Float>()
var statusValues = arrayListOf<String>()
var dateHeartValues = arrayListOf<String>()
var heartRateGraph = false

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TrackScreen(
    navController : NavController
) {
    val bloodPressureViewModel : BloodPressureReadingsViewModel = hiltViewModel()
    bloodPressureViewModel.getLast5Readings()
    bloodPressureViewModel.getAllReadings()

    val heartRateViewModel : HeartRateReadingsViewModel = hiltViewModel()
    heartRateViewModel.getLast5HeartReadings()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Track", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                },
                actions = {

                },
                backgroundColor = Color.White,
                elevation = 10.dp
            )
        },
        content = {
            Column (
                modifier = Modifier
                    .padding(10.dp)
                    ) {
                Card (

                        ) {
                    when (val response = bloodPressureViewModel.bloodPressure5ReadingData.value) {
                        is Response.Loading -> {
                            CircularProgressIndicator()
                        }
                        is Response.Success -> {
                            val obj = response.data
                            Column(

                            ) {
                                TrackContent(obj)
                            }
                        }
                        is Response.Error -> {
                            Toast(message = response.message)
                        }
                    }
                }

                Spacer(
                    modifier = Modifier
                        .padding(5.dp)
                )

                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    when (val response = bloodPressureViewModel.bloodPressureReadingData.value) {
                        is Response.Loading -> {
                            CircularProgressIndicator()
                        }
                        is Response.Success -> {
                            val obj = response.data
                            Column(

                            ) {
                                ListOfReadings(obj)
                            }
                        }
                        is Response.Error -> {
                            Toast(message = response.message)
                        }
                    }
                }

                Spacer(
                    modifier = Modifier
                        .padding(5.dp)
                )

                if(bloodPressureGraph) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        shape = RoundedCornerShape(15.dp),
                        elevation = 5.dp,
                        backgroundColor = checkReading(
                            systolic5Values[4].toInt(),
                            diastolic5Values[4].toInt()
                        )
                    ) {
                        Text(
                            text = checkReadingText(
                                systolic5Values[4].toInt(),
                                diastolic5Values[4].toInt()
                            ),
                            fontWeight = FontWeight.Bold,
                            lineHeight = 20.sp,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(10.dp)
                        )

                    }
                }

               /* when (val response = heartRateViewModel.heartRate5ReadingData.value) {
                    is Response.Loading -> {
                        CircularProgressIndicator()
                    }
                    is Response.Success -> {
                        val obj = response.data
                        Column(

                        ) {
                            TrackContentHeart(obj)
                        }
                    }
                    is Response.Error -> {
                        Toast(message = response.message)
                    }
                }*/
            }

        },
        bottomBar = {
            BottomNavigationMenu(selectedItem = BottomNavigationItem.TRACK, navController = navController)
        }
    )
}

@Composable
fun TrackContent(bloodPressureReadings: List<BloodPressureReadings>) {
    LazyColumn(
    ) {
        items(items = bloodPressureReadings, itemContent =  {
            ListContent(it)
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
            val chartEntryModel = ChartEntryModelProducer(getSys(), getDia())

            ComposeChart6(chartEntryModelProducer = chartEntryModel)
        }
    }
}

@Composable
fun ListContent(it: BloodPressureReadings) {
    val sys = it.systolicPressure.toFloat()
    val dia = it.diastolicPressure.toFloat()

    systolic5Values.add(0,sys)
    diastolic5Values.add(0,dia)

    var test = Calendar.getInstance()
    test.time = it.timestamp?.toDate()
    var date = (test.get(Calendar.DAY_OF_MONTH).toString()) + "/" + ((test.get(Calendar.MONTH) + 1).toString())

    date5Values.add(0,date)

}

@Composable
fun ListOfReadingsBar(it: BloodPressureReadings) {

    var test = Calendar.getInstance()
    test.time = it.timestamp?.toDate()
    var date = (test.get(Calendar.DAY_OF_MONTH).toString()) + "/" + ((test.get(Calendar.MONTH) + 1).toString())

    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Canvas(
            modifier = Modifier
                .size(size = 50.dp)
        ) {
            drawCircle(
                color = checkReading(it.systolicPressure, it.diastolicPressure)
            )
        }
        Text(text = "Systolic: " + it.systolicPressure.toString())
        Text(text = "Diastolic: " + it.diastolicPressure.toString())
        Text(text = "Date: $date")
    }
}

@Composable
fun ListOfReadings(bloodPressureReadings: List<BloodPressureReadings>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight(0.6f)
    ) {
        items(items = bloodPressureReadings, itemContent =  {
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            ) {
                ListOfReadingsBar(it)
            }
        })
    }
}

@Composable
fun TrackContentHeart(heartRateReadings: List<HeartRateReadings>) {
    LazyColumn {
        items(items = heartRateReadings, itemContent =  {
            ListContentHeart(it)
            heartRateGraph = true
        })
    }

    if (heartRateGraph) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(20.dp)
        ) {
            fun getBpm() = List(5) { FloatEntry(it.toFloat(), bpmValues.elementAt(it)) }
            //fun getStatus() = List(5) { FloatEntry(it.toFloat(), statusValues.elementAt(it)) }
            //val chartEntryModel = ChartEntryModelProducer(getBpm(), getStatus())

            //ComposeChart6(chartEntryModelProducer = chartEntryModel)
        }
    }

}

@Composable
fun ListContentHeart(it: HeartRateReadings) {
    val bpm = it.bpm.toFloat()
    val status = it.readingStatus

    bpmValues.add(0,bpm)
    statusValues.add(0,status)

    Text(text = it.bpm.toString())
    Text(text = it.readingStatus)

    var cal = Calendar.getInstance()
    cal.time = it.timestamp?.toDate()
    var date = (cal.get(Calendar.DAY_OF_MONTH).toString()) + "/" + ((cal.get(Calendar.MONTH) + 1).toString())

    dateHeartValues.add(0,date)
}

fun checkReading(systolic: Int, diastolic: Int): Color {
    var colour : Color
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
    var text : String
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
internal fun ComposeChart6(chartEntryModelProducer: ChartEntryModelProducer, modifier: Modifier = Modifier) {
    ProvideChartStyle(rememberChartStyle(chartColors)) {
        val defaultColumns = currentChartStyle.columnChart.columns
        Chart(
            chart = columnChart(
                columns = remember(defaultColumns) {
                    defaultColumns.map { defaultColumn ->
                        LineComponent(
                            defaultColumn.color,
                            defaultColumn.thicknessDp,
                        )
                    }
                },
                mergeMode = ColumnChart.MergeMode.Grouped,
            ),
            chartModelProducer = chartEntryModelProducer,
            modifier = modifier,
            startAxis = startAxis(),
            bottomAxis = bottomAxis(valueFormatter = bottomAxisValueFormatter),
            marker = rememberMarker(),
            legend = rememberLegend()
        )
    }
}

@Composable
private fun rememberLegend() =
    verticalLegend(
        items = chartColors.mapIndexed { index, chartColor ->
            name = if (index == 0) {
                "Systolic Pressure"
            } else {
                "Diastolic Pressure"
            }
            verticalLegendItem(
                icon = shapeComponent(Shapes.pillShape, chartColor),
                label = textComponent(
                    color = currentChartStyle.axis.axisLabelColor,
                    textSize = legendItemLabelTextSize,
                    typeface = Typeface.MONOSPACE,
                ),
                labelText = name,
            )
        },
        iconSize = legendItemIconSize,
        iconPadding = legendItemIconPaddingValue,
        spacing = legendItemSpacing,
        padding = legendPadding,
    )


private val color1 = Color.Blue //1st bar
private val color2 = Color.Green //2nd bar
private val color3 = Color.Yellow//unknown
private val color4 = Color.Red//Threshold
private val chartColors = listOf(color1, color2)
private val bottomAxisValueFormatter =
    AxisValueFormatter<AxisPosition.Horizontal.Bottom> { x, _ -> date5Values[x.toInt() % date5Values.size] }
private val legendItemLabelTextSize = 12.sp
private val legendItemIconSize = 8.dp
private val legendItemIconPaddingValue = 10.dp
private val legendItemSpacing = 4.dp
private val legendTopPaddingValue = 8.dp
private val legendPadding = dimensionsOf(top = legendTopPaddingValue)

var name = ""
