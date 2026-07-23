package com.example.soundinch9.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.soundinch9.ui.screens.LoginScreen
import com.example.soundinch9.ui.screens.MainScreen
import com.example.soundinch9.ui.screens.RegisterScreen
import com.example.soundinch9.ui.viewmodel.UserSessionViewModel

@Composable
fun SoundInNavGraph(
    navController: NavHostController,
    userSessionViewModel: UserSessionViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = SoundInRoutes.LOGIN
    ) {
        composable(SoundInRoutes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(SoundInRoutes.REGISTER)
                },
                onLoginSuccess = { email ->
                    val displayName = email.substringBefore("@")
                    userSessionViewModel.login(displayName, email)
                    navController.navigate(SoundInRoutes.MAIN) {
                        popUpTo(SoundInRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(SoundInRoutes.MAIN) {
            MainScreen()
        }
        composable(SoundInRoutes.REGISTER) {
            RegisterScreen()
        }
    }
}