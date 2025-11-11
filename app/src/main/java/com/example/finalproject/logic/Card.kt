package com.example.finalproject.logic

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// A card has a front and back side.
abstract class Card {
    // The composable to render the front of the card.
    abstract fun front(): @Composable () -> Unit

    // The composable to render the back of the card.
    abstract fun back(): @Composable () -> Unit
}

class BasicCard(val frontContent: Any, val backContent: Any): Card() {
    override fun front(): @Composable (() -> Unit) {
        return { Text(frontContent.toString()) }
    }

    override fun back(): @Composable (() -> Unit) {
        return { Text(backContent.toString()) }
    }
}
