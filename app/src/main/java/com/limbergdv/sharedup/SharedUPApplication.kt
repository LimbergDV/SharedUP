package com.limbergdv.sharedup

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.limbergdv.sharedup.features.authentication.presentation.screens.LoginScreen
import com.limbergdv.sharedup.features.authentication.presentation.screens.RegisterScreen
import com.limbergdv.sharedup.features.home.presentation.screens.HomeScreen

@Composable
fun SharedUPApplication() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onRegisterClick = { navController.navigate("register") },
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onLoginClick = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen()
        }
    }
}