package com.example.kuttykoriander.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kuttykoriander.data.UserPreferences
import com.example.kuttykoriander.ui.screens.HomeScreen
import com.example.kuttykoriander.ui.screens.OnboardingScreen
import com.example.kuttykoriander.ui.screens.ProfileScreen

@Composable
fun LittleLemonNavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val userPreferences = UserPreferences(context)
    val startDestination = if (userPreferences.isLoggedIn) "home" else "onboarding"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("onboarding") {
            OnboardingScreen(
                onRegisterSuccess = {
                    navController.navigate("home") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                },
                onBrowseMenu = {
                    navController.navigate("home") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }
        composable("home") {
            HomeScreen(
                onProfileClick = {
                    navController.navigate("profile")
                },
                onTabSelected = { tab ->
                    if (tab == "profile") {
                        navController.navigate("profile")
                    }
                }
            )
        }
        composable("profile") {
            ProfileScreen(
                onLogOut = {
                    navController.navigate("onboarding") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onTabSelected = { tab ->
                    if (tab == "home") {
                        navController.navigate("home") {
                            popUpTo("profile") { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}
