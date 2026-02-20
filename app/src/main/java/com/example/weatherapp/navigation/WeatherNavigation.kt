package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.SearchScreen
import com.example.weatherapp.WeatherScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "search"
    ) {
        composable("search") {
            SearchScreen(navController)
        }
        composable("weather/{city}") { backStackEntry ->
            val city = backStackEntry.arguments?.getString("city") ?: ""
            WeatherScreen(city)
        }
    }
}