package dev.havir.unit4_project2_unscramble.ui

data class GameUiState(
    val scrambledWord: String = "",
    val score: Int = 0,
    val wordCount: Int = 0,
    val hasError: Boolean = false,
    val isGameOver: Boolean = false,
)
