package com.example.countriesapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.countriesapp.common.Constants
import com.example.countriesapp.common.Constants.All_Country
import com.example.countriesapp.common.Constants.Currency
import com.example.countriesapp.common.Constants.DIFFICULT
import com.example.countriesapp.common.Constants.Region
import com.example.countriesapp.common.Constants.Region_Name
import com.example.countriesapp.common.Constants.Sub_Region
import com.example.countriesapp.presentation.country_detail.screen.CountryDetailPage
import com.example.countriesapp.presentation.country_list.screen.CountryListScreen
import com.example.countriesapp.presentation.currency.CurrencyPage
import com.example.countriesapp.presentation.home.HomeScreen
import com.example.countriesapp.presentation.play_quiz.screen.PlayQuiz
import com.example.countriesapp.presentation.play_quiz.screen.detail.QuizPage
import com.example.countriesapp.presentation.play_quiz.screen.shared_difficult.SharedDifficultyScreen
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
                if (it == All_Country) {
                    navController.navigate(route = Screen.CountryPage.route)
                }
                if (it == Region) {
                    navController.navigate(route = Screen.RegionPage.route)
                }
                if (it == Sub_Region) {
                    navController.navigate(route = Screen.SubRegionPage.route)
                }
                if (it == Currency) {
                    navController.navigate(route = Screen.CurrencyPage.route)
                }
                if (it == Constants.Play_Quiz) {
                    navController.navigate(route = Screen.PlayQuizPage.route)
                }
                if (it == Constants.Favorite) {
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
            route = Screen.RegionCountryListPage.route + "/{$Region_Name}",
            arguments = listOf(
                navArgument(name = Region_Name) {
                    type = NavType.StringType
                })
        ) {
            RegionCountryList(backClick = {
                navController.popBackStack()
                navController.currentBackStackEntry?.savedStateHandle?.remove<String>(Region_Name)
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
                    navController.navigate(Screen.HomePage.route)
                },
                clickHomeItem = {quizLevel->
                    navController.navigate(route = Screen.SharedDifficultyPage.route+"/${quizLevel}")
                    if (quizLevel==Constants.EUROPE){
                        navController.currentBackStackEntry?.savedStateHandle?.set(DIFFICULT, Constants.EUROPE)
                        navController.navigate(route = Screen.QuizDetailPage.route)
                    }
                    else if (quizLevel==Constants.AMERICA){
                        navController.currentBackStackEntry?.savedStateHandle?.set(DIFFICULT, Constants.AMERICA)
                        navController.navigate(route = Screen.QuizDetailPage.route)
                    }
                    else if (quizLevel==Constants.AFRICA){
                        navController.currentBackStackEntry?.savedStateHandle?.set(DIFFICULT, Constants.AFRICA)
                        navController.navigate(route = Screen.QuizDetailPage.route)
                    }
                    else if (quizLevel==Constants.ASIA){
                        navController.currentBackStackEntry?.savedStateHandle?.set(DIFFICULT, Constants.ASIA)
                        navController.navigate(route = Screen.QuizDetailPage.route)
                    }
                    else if (quizLevel==Constants.OCEANIA){
                        navController.currentBackStackEntry?.savedStateHandle?.set(DIFFICULT, Constants.OCEANIA)
                        navController.navigate(route = Screen.QuizDetailPage.route)
                    }
                    else{
                        navController.navigate(route = Screen.SharedDifficultyPage.route+"/${quizLevel}")
                    }
                })
        }
        composable(route = Screen.QuizDetailPage.route) {
            QuizPage(
                backClick = {
                    if (it?.isNotEmpty() == true){
                        //bunu ekleme sebebim regionlardan geriye tıklayınca zorluk sayfasına yönelmemeli.
                        navController.navigate(Screen.PlayQuizPage.route)
                    }else{
                        navController.popBackStack()
                    }
                },
                navController = navController
            )
        }

        composable(route ="${Screen.SharedDifficultyPage.route}/{${DIFFICULT}}",
            arguments = listOf(
                navArgument(name = DIFFICULT) {
                    type = NavType.StringType
                })
            )
         {difficultLevel->
             difficultLevel.arguments?.getString(DIFFICULT)?.let {
                 SharedDifficultyScreen(
                     difficultLevel = it,
                     backClick = {
                         navController.navigate(Screen.PlayQuizPage.route)
                         navController.currentBackStackEntry?.savedStateHandle?.remove<String>(DIFFICULT)
                     },
                     chooseCategoryData = {quizItem ,diffLevel->
                         navController.currentBackStackEntry?.savedStateHandle?.set(Screen.QuizDetailPage.route, quizItem)

                         navController.currentBackStackEntry?.savedStateHandle?.set(DIFFICULT, diffLevel)

                         navController.navigate(route = Screen.QuizDetailPage.route)
                     })
             }
        }
    }
}

val NavHostController.canGoBack:Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState==Lifecycle.State.RESUMED