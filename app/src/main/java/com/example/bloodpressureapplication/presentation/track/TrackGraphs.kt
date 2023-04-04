package com.example.bloodpressureapplication.presentation.track

import android.graphics.Typeface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloodpressureapplication.util.rememberChartStyle
import com.example.bloodpressureapplication.util.rememberMarker
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.legend.verticalLegend
import com.patrykandpatrick.vico.compose.legend.verticalLegendItem
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.axis.formatter.PercentageFormatAxisValueFormatter
import com.patrykandpatrick.vico.core.axis.horizontal.HorizontalAxis
import com.patrykandpatrick.vico.core.chart.column.ColumnChart
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer

private val color1 = Color.Blue //1st bar
private val color2 = Color.Green //2nd bar
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

private const val COLUMN_WIDTH_DP = 16f
private const val START_AXIS_LABEL_COUNT = 5
private const val BOTTOM_AXIS_TICK_OFFSET = 1
private const val BOTTOM_AXIS_TICK_SPACING = 3

private val color3 = Color.Blue
private val heartChartColors = listOf(color3)
private val startAxisValueFormatter = PercentageFormatAxisValueFormatter<AxisPosition.Vertical.Start>()
private val heartBottomAxisValueFormatter =
    AxisValueFormatter<AxisPosition.Horizontal.Bottom> { x, _ -> date5HeartValues[x.toInt() % date5HeartValues.size] }


@Composable
internal fun BloodPressureGraph(chartEntryModelProducer: ChartEntryModelProducer, modifier: Modifier = Modifier) {
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
            legend = bloodRememberLegend()
        )
    }
}

@Composable
private fun bloodRememberLegend() =
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



@Composable
private fun heartRememberLegend() =
    verticalLegend(
        items = heartChartColors.mapIndexed { index, chartColor ->
            verticalLegendItem(
                icon = shapeComponent(Shapes.pillShape, chartColor),
                label = textComponent(
                    color = currentChartStyle.axis.axisLabelColor,
                    textSize = legendItemLabelTextSize,
                    typeface = Typeface.MONOSPACE,
                ),
                labelText = "BPM",
            )
        },
        iconSize = legendItemIconSize,
        iconPadding = legendItemIconPaddingValue,
        spacing = legendItemSpacing,
        padding = legendPadding,
    )


@Composable
internal fun HeartRateGraph(chartEntryModelProducer: ChartEntryModelProducer, modifier: Modifier = Modifier) {
    ProvideChartStyle(rememberChartStyle(heartChartColors)) {
        val defaultColumns = currentChartStyle.columnChart.columns
        Chart(
            chart = columnChart(
                columns = remember(defaultColumns) {
                    defaultColumns.map { defaultColumn ->
                        LineComponent(defaultColumn.color, COLUMN_WIDTH_DP, defaultColumn.shape)
                    }
                },
            ),
            chartModelProducer = chartEntryModelProducer,
            modifier = modifier,
            startAxis = startAxis(),
            bottomAxis = bottomAxis(valueFormatter = heartBottomAxisValueFormatter),
            marker = rememberMarker(),
            legend = heartRememberLegend()
        )
    }
}