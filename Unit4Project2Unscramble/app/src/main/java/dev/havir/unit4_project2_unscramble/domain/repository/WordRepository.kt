package dev.havir.unit4_project2_unscramble.domain.repository

interface WordRepository {
    fun fetchWords(): List<String>
}