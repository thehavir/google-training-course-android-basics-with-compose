package dev.havir.unit4_project1_dessertclicker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.havir.unit4_project1_dessertclicker.data.DessertDatasource
import dev.havir.unit4_project1_dessertclicker.ui.dessertclicker.DessertClickerScreen
import dev.havir.unit4_project1_dessertclicker.ui.dessertclicker.DessertClickerViewModelFactory
import dev.havir.unit4_project1_dessertclicker.ui.theme.Unit4Project1DessertClickerTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreated called")
        enableEdgeToEdge()
        setContent {
            val factory = DessertClickerViewModelFactory(DessertDatasource())
            Unit4Project1DessertClickerTheme {
                DessertClickerScreen(factory = factory)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "onStart called")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d(TAG, "onRestart called")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()

        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy called")
    }
}
