package com.example.countriesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.countriesapp.presentation.country_list.screen.CountryList

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CountryPage.route
    ) {
        composable(route = Screen.CountryPage.route){
            CountryList()
        }

        composable(route = Screen.CountryDetailPage.route){

        }
    }
}