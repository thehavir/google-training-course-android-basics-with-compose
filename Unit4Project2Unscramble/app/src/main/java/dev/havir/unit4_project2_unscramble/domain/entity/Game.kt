package dev.havir.unit4_project2_unscramble.domain.entity

data class Game(
    val word: String = "",
    val scrambledWord: String = "",
    val score: Int = 0,
    val wordCount: Int = 0,
    private val usedWords: Set<String> = emptySet(),
) {
    private companion object {
        const val MAX_NO_OF_WORDS = 10
        const val SCORE_INCREASE = 20
    }

    val isGameOver: Boolean
        get() = wordCount == MAX_NO_OF_WORDS

    fun isWordUsed(word: String) = usedWords.contains(word)

    fun isGuessCorrect(guess: String) =
        guess.trim().equals(word, ignoreCase = true)

    fun scored() = copy(score = score.plus(SCORE_INCREASE))

    fun withNexWord(nextWord: String) = copy(
        word = nextWord,
        scrambledWord = scramble(nextWord),
        usedWords = usedWords.plus(nextWord),
        wordCount = wordCount.inc()
    )

    private fun scramble(word: String): String {
        val chars = word.toCharArray()
        do {
            chars.shuffle()
        } while (word == String(chars))

        return String(chars)
    }
}
