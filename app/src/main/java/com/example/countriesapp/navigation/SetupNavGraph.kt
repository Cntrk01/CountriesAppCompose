package com.example.countriesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CountryPage.route
    ) {
        composable(route = Screen.CountryPage.route){

        }

        composable(route = Screen.CountryDetailPage.route){

        }
    }
}