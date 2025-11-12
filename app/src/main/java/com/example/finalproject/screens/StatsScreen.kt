package com.example.finalproject.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.PopupProperties
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale
import kotlin.random.Random

@Composable
fun StatsScreen() {
    LazyColumn {
        item {
            Text("StatsScreen test text")
        }
        item {
            CardActivityColumnChart(Modifier)
        }
    }
}

@Composable
@Preview(widthDp = 320, heightDp = 320, showBackground = true)
fun CardActivityColumnChart(modifier: Modifier = Modifier) {
    ColumnChart(
        modifier = modifier
            .height(320.dp)
            .padding(
                PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 64.dp
                )
            ),
        data = remember {
            List(7) {
                Bars(
                    label = DayOfWeek.entries[it].getDisplayName(
                        TextStyle.FULL,
                        Locale.getDefault()
                    ),
                    values = listOf(
                        Bars.Data(
                            label = "Correct",
                            value = Random.nextInt(250).toDouble(),
                            color = SolidColor(Color.Green)
                        ), Bars.Data(
                            label = "Incorrect",
                            value = Random.nextInt(35).toDouble(),
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
