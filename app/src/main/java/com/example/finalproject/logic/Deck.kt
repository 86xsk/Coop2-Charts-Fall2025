package com.example.finalproject.logic

// A deck provides cards.
abstract class Deck(
    val name: String = "Unnamed deck",
) {
    // Get the next card that should be displayed.
    abstract fun getNextCard(): DeckCard

    // Submit an answer for a card.
    abstract fun submitAnswer(card: DeckCard, answer: Answer)

    // The number of cards in the deck
    abstract fun size(): Int
}

// A deck using the Lietner system for scheduling cards.
// https://en.wikipedia.org/wiki/Leitner_system
class LietnerDeck(
    name: String,
    numBoxes: Int,
    cards: MutableList<Card>,
): Deck(name) {
    private typealias Box = MutableList<DeckCard>
    private val boxes: List<Box> =
        List(numBoxes) {
            if (it == 0) cards.map { card ->
                DeckCard(
                    card = card,
                    deck = this
                )
            }.toMutableList() else mutableListOf()
        }

    init {
        require(numBoxes > 0)
    }

    override fun getNextCard(): DeckCard {
        return boxes.first { box -> !box.isEmpty() }.first()
    }

    override fun submitAnswer(card: DeckCard, answer: Answer) {
        require(card.deck === this)
        val boxIndex = findBoxIndexOfCard(card)
        when (answer) {
            Answer.Good -> {
                // Move card up one box, if not already in the last box.
                if (boxIndex != boxes.lastIndex) {
                    moveCard(card, boxes[boxIndex], boxes[boxIndex + 1])
                }
            }
            Answer.Again -> {
                // Move card to the first box.
                moveCard(card, boxes[boxIndex], boxes.first())
            }
        }
    }

    override fun size(): Int {
        return boxes.sumOf { box -> box.count() }
    }

    // Find the index of the box which contains the provided card.
    private fun findBoxIndexOfCard(card: DeckCard): Int {
        return boxes.indexOfFirst { box -> box.contains(card) }
    }

    // Move the given card to a different box.
    private fun moveCard(card: DeckCard, srcBox: Box, dstBox: Box) {
        srcBox.remove(card)
        dstBox.add(card)
    }
}

// A card that belongs to a deck.
class DeckCard(val card: Card, val deck: Deck) {
    // Provide an answer for this card.
    fun answer(answer: Answer) {
        deck.submitAnswer(this, answer)
    }
}
