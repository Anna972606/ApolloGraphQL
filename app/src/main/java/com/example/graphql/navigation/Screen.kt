package com.example.graphql.navigation

sealed class Screen(val route: String) {
    object CountryListScreen : Screen(Route.COUNTRY_LIST_SCREEN)
}