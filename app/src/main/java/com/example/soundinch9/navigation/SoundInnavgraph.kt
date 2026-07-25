package com.example.soundinch9.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.soundinch9.ui.UserSessionViewModel
import com.example.soundinch9.ui.screens.LoginScreen
import com.example.soundinch9.ui.screens.MainScreen
import com.example.soundinch9.ui.screens.RegisterScreen

@Composable
fun SoundInNavGraph(
    navController: NavHostController,
    sessionViewModel: UserSessionViewModel
) {
    NavHost(
        navController = navController,
        startDestination = SoundInRoutes.LOGIN
    ) {
        composable(SoundInRoutes.LOGIN) {
            LoginScreen(
                sessionViewModel = sessionViewModel,


                onNavigateToRegister = {
                    navController.navigate(SoundInRoutes.REGISTER)
                },
                onLoginSuccess = { email ->
                    navController.navigate(SoundInRoutes.MAIN) {
                        popUpTo(SoundInRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(SoundInRoutes.MAIN) {
            MainScreen(
                sessionViewModel = sessionViewModel,
                onLogout = {
                    sessionViewModel.onLogout()
                    navController.navigate(SoundInRoutes.LOGIN) {
                        popUpTo(SoundInRoutes.MAIN) { inclusive = true }
                    }
                },
                onNavigateToPlaylistDetail = { playlist ->
                    navController.navigate("playlistDetail/${playlist.id}")}
            )
        }
        composable(SoundInRoutes.REGISTER) {
            RegisterScreen()
        }
    }
}