package dev.havir.unit4_project2_unscramble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dev.havir.unit4_project2_unscramble.data.LocalWordRepository
import dev.havir.unit4_project2_unscramble.domain.usecase.CheckGuessUseCase
import dev.havir.unit4_project2_unscramble.domain.usecase.ResetGameUseCase
import dev.havir.unit4_project2_unscramble.domain.usecase.SetNextRoundUseCase
import dev.havir.unit4_project2_unscramble.ui.GameScreen
import dev.havir.unit4_project2_unscramble.ui.GameViewModelFactory
import dev.havir.unit4_project2_unscramble.ui.theme.Unit4Project2UnscrambleTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val wordRepository = LocalWordRepository()
        val resetGameUseCase = ResetGameUseCase(
            wordRepository = wordRepository,
            random = Random,
        )
        val setNextRoundUseCase = SetNextRoundUseCase(
            wordRepository = wordRepository,
            random = Random,
        )
        val checkGuessUseCase = CheckGuessUseCase()
        val factory = GameViewModelFactory(
            resetGameUseCase, setNextRoundUseCase, checkGuessUseCase
        )

        setContent {
            Unit4Project2UnscrambleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    GameScreen(factory = factory, onExit = ::exitGame)
                }
            }
        }
    }

    internal fun exitGame() = finish()
}
