package com.example.graphql.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.graphql.presentation.ui.CountryListScreen

@Composable
fun RootNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.CountryListScreen.route) {
        composable(Screen.CountryListScreen.route) { CountryListScreen() }
    }
}