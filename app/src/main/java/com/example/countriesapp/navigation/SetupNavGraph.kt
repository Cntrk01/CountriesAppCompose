package com.example.countriesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.countriesapp.common.Constants.ALL_COUNTRY
import com.example.countriesapp.common.Constants.REGION
import com.example.countriesapp.common.Constants.REGION_NAME
import com.example.countriesapp.presentation.country_detail.screen.CountryDetailPage
import com.example.countriesapp.presentation.country_list.screen.CountryListScreen
import com.example.countriesapp.presentation.home.HomeScreen
import com.example.countriesapp.presentation.region_subregion.region.screen.RegionScreen
import com.example.countriesapp.presentation.region_subregion.regionlist.screen.RegionCountryList

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomePage.route
    ) {
        composable(route = Screen.HomePage.route) {
            HomeScreen(clickHomeItem = {
                if (it == ALL_COUNTRY) {
                    navController.navigate(route = Screen.CountryPage.route)
                }
                if (it == REGION) {
                    navController.navigate(route = Screen.RegionPage.route)
                }
            })
        }
        composable(route = Screen.CountryPage.route) {
            CountryListScreen(clickCountry = { countryItem ->
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    Screen.CountryDetailPage.route,
                    countryItem
                )
                navController.navigate(route = Screen.CountryDetailPage.route)
            },
                backClick = {
                    navController.popBackStack()
                })
        }

        composable(route = Screen.CountryDetailPage.route) {
            //navController.previousBackStackEntry?.savedStateHandle?.get<CountryDetailItem>(Screen.CountryDetailPage.route)
            CountryDetailPage(navController = navController, backClick = {
                navController.popBackStack()
            })
        }

        composable(route = Screen.RegionPage.route) {
            RegionScreen(backClick = {
                navController.popBackStack()
            }, clickRegionItem = { region ->
                navController.navigate(route = Screen.RegionCountryListPage.route + "/$region")
            })
        }

        composable(
            route = Screen.RegionCountryListPage.route + "/{$REGION_NAME}",
            arguments = listOf(
                navArgument(name = REGION_NAME) {
                    type = NavType.StringType
                })
        ) {
            RegionCountryList(backClick = {
                navController.popBackStack()
            }, clickCountry = {countryItem->
                navController.currentBackStackEntry?.savedStateHandle?.set(Screen.CountryDetailPage.route, countryItem)

                navController.navigate(route = Screen.CountryDetailPage.route)
            })
        }
    }
}