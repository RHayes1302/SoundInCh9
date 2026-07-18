package com.example.soundinch9.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.soundinch9.ui.screens.LoginScreen
import com.example.soundinch9.ui.screens.RegisterScreen

private object SoundInDestinations {
    const val LOGIN = "login"
    const val REGISTER = "register"
}

@Composable
fun SoundInNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SoundInDestinations.LOGIN
    ) {
        composable(SoundInDestinations.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(SoundInDestinations.REGISTER)
                }
            )
        }
        composable(SoundInDestinations.REGISTER) {
            RegisterScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}