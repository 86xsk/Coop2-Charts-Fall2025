package com.example.finalproject.screens

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.example.finalproject.logic.BasicCard
import com.example.finalproject.logic.Deck
import com.example.finalproject.logic.LietnerDeck

@Composable
fun PracticeScreen() {
  Text("Choose a deck:")
    DeckColumns(GetDecks(), Modifier)
}

// todo: study screen
@Composable
fun DeckColumns(decks: List<Deck>, modifier: Modifier) {
  LazyColumn(
    modifier = modifier
  ) {
    items(decks) { deck ->
      Card(
        modifier = modifier
          .fillMaxWidth()
          .padding(PaddingValues(vertical = 3.dp, horizontal = 3.dp))
          .border(width = 2.dp, color = Color.Black, shape = CardDefaults.shape)
      ) {
        Text(deck.name)
        Text("Cards: ${deck.size}")
      }
    }
  }
}

fun GetDecks(): List<Deck> {
  return listOf(
    LietnerDeck(
        name = "Binary-decimal conversion",
        numBoxes = 3,
        cards = MutableList(255) {
            BasicCard(it, it.toString(2))
        })
  )
}
