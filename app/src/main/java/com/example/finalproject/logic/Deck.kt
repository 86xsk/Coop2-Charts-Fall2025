package com.example.finalproject.logic

// A deck provides cards.
abstract class Deck(
    val name: String = "Unnamed deck",
) {
    // Get the next card that should be displayed.
    abstract fun getNextCard(): Card

    // The number of cards in the deck
    abstract fun Size(): Int
}
