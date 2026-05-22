package dev.havir.unit4_project2_unscramble.data

import dev.havir.unit4_project2_unscramble.domain.repository.WordRepository

class LocalWordRepository : WordRepository {
    override fun fetchWords() = allWords.toList()
}
