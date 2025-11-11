package com.example.finalproject.logic

import androidx.compose.runtime.Composable

// A card has a front and back side.
// A card also stores some statistics regarding itself.
abstract class Card {
    // The composable to render the front of the card.
    abstract fun front(): @Composable () -> Unit

    // The composable to render the back of the card.
    abstract fun back(): @Composable () -> Unit
}
