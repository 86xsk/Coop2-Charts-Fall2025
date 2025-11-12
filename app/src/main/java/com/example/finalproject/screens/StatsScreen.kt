package com.example.finalproject.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalproject.logic.DayData
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.Pie
import ir.ehsannarmani.compose_charts.models.PopupProperties
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale
import kotlin.random.Random

@Composable
fun StatsScreen() {
    val dayData = generateTestData(7)
    val graphModifier = Modifier
        .height(320.dp)
        .padding(
            PaddingValues(
                start = 16.dp,
                end = 16.dp,
                bottom = 64.dp
            )
        )
    LazyColumn {
        item {
            Text("Activity by weekday")
        }
        item {
            WeekdayActivityColumnChart(
                data = dayData,
                modifier = graphModifier
            )
        }
        item {
            Text("Last 7 days")
        }
        item {
            ActivityPieChart(
                data = dayData,
                modifier = graphModifier
            )
        }
    }
}

@Composable
@Preview(widthDp = 320, heightDp = 320, showBackground = true)
fun ActivityPieChart(
    data: List<DayData> = generateTestData(7),
    modifier: Modifier = Modifier
) {
    val totalCorrect = data.sumOf { it.numCorrect }
    val totalIncorrect = data.sumOf { it.numIncorrect }
    var slices by remember {
        mutableStateOf(
            listOf(
                Pie(
                    label = "Correct", data = totalCorrect.toDouble(), color = Color.Green
                ),
                Pie(
                    label = "Incorrect", data = totalIncorrect.toDouble(), color = Color.Red
                )
            )
        )
    }
    PieChart(
        modifier = modifier.fillMaxWidth(0.9f),
        data = slices,
        selectedScale = 1.2f,
        onPieClick = {
            slices = slices.mapIndexed { i, pie ->
                val targetIndex = slices.indexOf(it)
                pie.copy(selected = targetIndex == i)
            }.toMutableList()
        },
        style = Pie.Style.Stroke(32.dp)
    )
}

@Composable
@Preview(widthDp = 320, heightDp = 320, showBackground = true)
fun WeekdayActivityColumnChart(
    data: List<DayData> = generateTestData(7),
    modifier: Modifier = Modifier
) {
    ColumnChart(
        modifier = modifier,
        data = remember {
            data.mapIndexed { i, data ->
                Bars(
                    label = DayOfWeek.entries[i].getDisplayName(
                        TextStyle.FULL,
                        Locale.getDefault()
                    ),
                    values = listOf(
                        Bars.Data(
                            label = "Correct",
                            value = data.numCorrect.toDouble(),
                            color = SolidColor(Color.Green)
                        ), Bars.Data(
                            label = "Incorrect",
                            value = data.numIncorrect.toDouble(),
                            color = SolidColor(Color.Red)
                        )
                    ),
                )
            }
        },
        barProperties = BarProperties(
            spacing = 0.dp,
            cornerRadius = Bars.Data.Radius.Circular(2.dp),
        ),
        indicatorProperties = HorizontalIndicatorProperties(
            contentBuilder = { indicatorValue ->
                "%.${0}f".format(indicatorValue)
            },
        ),
        popupProperties = PopupProperties(
            contentBuilder = { popUp ->
                "%.${0}f".format(popUp.value)
            },
            textStyle = androidx.compose.ui.text.TextStyle().copy(
                color = Color.White,
                fontSize = 12.sp
            )
        )
    )
}

fun generateTestData(days: Int): List<DayData> {
    return List(days) {
        DayData(Random.nextInt(250), Random.nextInt(32))
    }
}
