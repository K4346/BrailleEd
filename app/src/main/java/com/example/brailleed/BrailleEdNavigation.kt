package com.example.brailleed

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.brailleed.ui.screens.braille_trainer_screen.BrailleTrainerScreen
import com.example.brailleed.ui.screens.MainScreen
import com.example.brailleed.ui.screens.alphabet_screen.AlphabetScreen

@Composable
fun BrailleEdNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = BrailleEdRoutes.MainScreen.name
    ) {

        composable(BrailleEdRoutes.MainScreen.name) {
            MainScreen(navHostController = navController)
        }
        composable(BrailleEdRoutes.AlphabetScreen.name) {
            AlphabetScreen(navHostController = navController)
        }
        composable(BrailleEdRoutes.BrailleTrainerScreen.name) {
            BrailleTrainerScreen(navHostController = navController)
        }
    }
}

enum class BrailleEdRoutes {
    MainScreen, BrailleTrainerScreen, AlphabetScreen
}