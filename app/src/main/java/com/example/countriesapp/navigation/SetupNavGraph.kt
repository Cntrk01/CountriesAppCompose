package com.example.countriesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.countriesapp.presentation.country_detail.screen.CountryDetailPage
import com.example.countriesapp.presentation.country_list.screen.CountryListScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CountryPage.route
    ) {
        composable(route = Screen.CountryPage.route){
            CountryListScreen(clickCountry = { countryItem->
                navController.currentBackStackEntry?.savedStateHandle?.set(Screen.CountryDetailPage.route, countryItem)
                navController.navigate(route = Screen.CountryDetailPage.route)
            })
        }

        composable(route = Screen.CountryDetailPage.route){
            //navController.previousBackStackEntry?.savedStateHandle?.get<CountryDetailItem>(Screen.CountryDetailPage.route)
            CountryDetailPage(navController = navController, backClick = {
                navController.popBackStack()
            })
        }
    }
}