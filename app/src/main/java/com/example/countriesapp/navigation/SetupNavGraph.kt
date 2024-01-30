package com.example.countriesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.countriesapp.common.Constants
import com.example.countriesapp.common.Constants.ALL_COUNTRY
import com.example.countriesapp.common.Constants.CURRENCY
import com.example.countriesapp.common.Constants.REGION
import com.example.countriesapp.common.Constants.REGION_NAME
import com.example.countriesapp.common.Constants.SUB_REGION
import com.example.countriesapp.presentation.country_detail.screen.CountryDetailPage
import com.example.countriesapp.presentation.country_list.screen.CountryListScreen
import com.example.countriesapp.presentation.currency.CurrencyPage
import com.example.countriesapp.presentation.home.HomeScreen
import com.example.countriesapp.presentation.play_quiz.screen.PlayQuiz
import com.example.countriesapp.presentation.play_quiz.screen.detail.QuizPage
import com.example.countriesapp.presentation.play_quiz.screen.easy.EasyPage
import com.example.countriesapp.presentation.region_subregion.region.screen.RegionScreen
import com.example.countriesapp.presentation.region_subregion.regionlist.screen.RegionCountryList
import com.example.countriesapp.presentation.region_subregion.subregion.SubRegionScreen

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
                if (it == SUB_REGION) {
                    navController.navigate(route = Screen.SubRegionPage.route)
                }
                if (it == CURRENCY) {
                    navController.navigate(route = Screen.CurrencyPage.route)
                }
                if (it == Constants.PLAY_QUIZ) {
                    navController.navigate(route = Screen.PlayQuizPage.route)
                }
                if (it == Constants.FAVORITE) {
                    //navController.navigate(route = Screen.PlayQuizPage.route)
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

        composable(route = Screen.SubRegionPage.route) {
            SubRegionScreen(backClick = {
                navController.popBackStack()
            }, clickSubRegionItem = { subRegion ->
                navController.navigate(route = Screen.RegionCountryListPage.route + "/$subRegion")
            })
        }

        composable(route = Screen.CurrencyPage.route) {
            CurrencyPage(backClick = {
                navController.popBackStack()
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
            }, clickCountry = { countryItem ->
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    Screen.CountryDetailPage.route,
                    countryItem
                )
                navController.navigate(route = Screen.CountryDetailPage.route)
            })
        }

        //QUIZ PAGESS

        composable(route = Screen.PlayQuizPage.route) {
            PlayQuiz(
                backClick = {
                    navController.popBackStack()
                },
                clickHomeItem = {
                    if (it == Constants.EASY) {
                        navController.navigate(route = Screen.EasyPage.route)
                    }
                    if (it == Constants.MEDIUM) {

                    }
                    if (it == Constants.HARD) {

                    }
                    if (it == Constants.EXPERT) {

                    }
                })
        }
        composable(route = Screen.QuizDetailPage.route) {
            QuizPage(
                backClick = {
                    navController.popBackStack()
                },
                navController = navController
            )
        }

        composable(route = Screen.EasyPage.route) {
            EasyPage(
                backClick = {
                    navController.popBackStack()
                },
                chooseCategoryData = {quizItem ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        Screen.QuizDetailPage.route,
                        quizItem
                    )
                    navController.navigate(route = Screen.QuizDetailPage.route)
                })
        }
    }
}