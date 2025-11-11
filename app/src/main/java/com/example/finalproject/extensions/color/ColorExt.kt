package com.example.finalproject.extensions.color

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Color

fun Color.darken(@FloatRange(0.0, 1.0) amount: Float) =
    this.copy(red = red * (1f - amount), green = green * (1f - amount), blue = blue * (1f - amount))

fun Color.lighten(@FloatRange(0.0, 1.0, fromInclusive = false) amount: Float) =
    this.copy(red = red / (1f - amount), green = green / (1f - amount), blue = blue / (1f - amount))
