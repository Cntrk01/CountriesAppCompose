package com.mckstudio.countriesapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mckstudio.countriesapp.common.Constants
import com.mckstudio.countriesapp.common.Constants.Country_Title
import com.mckstudio.countriesapp.common.Constants.Currency_Title
import com.mckstudio.countriesapp.common.Constants.DIFFICULT
import com.mckstudio.countriesapp.common.Constants.FAVORITE
import com.mckstudio.countriesapp.common.Constants.Region_Name
import com.mckstudio.countriesapp.common.Constants.Region_Title
import com.mckstudio.countriesapp.common.Constants.Sub_Region_Title
import com.mckstudio.countriesapp.presentation.country_detail.screen.CountryDetailScreen
import com.mckstudio.countriesapp.presentation.country_detail.vm.CountryDetailViewModel
import com.mckstudio.countriesapp.presentation.country_list.screen.CountryListScreen
import com.mckstudio.countriesapp.presentation.currency.CurrencyPage
import com.mckstudio.countriesapp.presentation.favorite.screen.FavoriteScreen
import com.mckstudio.countriesapp.presentation.home.HomeScreen
import com.mckstudio.countriesapp.presentation.play_quiz.screen.PlayQuiz
import com.mckstudio.countriesapp.presentation.play_quiz.screen.detail.QuizPage
import com.mckstudio.countriesapp.presentation.play_quiz.screen.shared_difficult.SharedDifficultyScreen
import com.mckstudio.countriesapp.presentation.region_subregion.region.screen.RegionScreen
import com.mckstudio.countriesapp.presentation.region_subregion.regionlist.screen.RegionCountryList
import com.mckstudio.countriesapp.presentation.region_subregion.subregion.SubRegionScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomePage.route,
    ) {
        composable(route = Screen.HomePage.route) {
            HomeScreen(
                clickHomeItem = {
                if (it == Country_Title) {
                    navController.navigate(route = Screen.CountryPage.route)
                }
                if (it == Region_Title) {
                    navController.navigate(route = Screen.RegionPage.route)
                }
                if (it == Sub_Region_Title) {
                    navController.navigate(route = Screen.SubRegionPage.route)
                }
                if (it == Currency_Title) {
                    navController.navigate(route = Screen.CurrencyPage.route)
                }
                if (it == Constants.Quiz_Title) {
                    navController.navigate(route = Screen.PlayQuizPage.route)
                }
                if (it == Constants.Favorite_Title) {
                    navController.navigate(route = Screen.FavoritePage.route + "/$FAVORITE")
                }
            },
                onSelectedCountry = { selectedCountry ->
                    navController.navigate(route = Screen.CountryDetailPage.route+"/${selectedCountry}")
                }
            )
        }
        composable(route = Screen.CountryPage.route) {
            CountryListScreen(
                clickCountry = { countryName ->
                    navController.navigate(route = Screen.CountryDetailPage.route+"/$countryName")

                },
                backClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Screen.CountryDetailPage.route+"/{countryName}") { backStackEntry ->

            val countryName = backStackEntry.arguments?.getString("countryName") ?: ""
            val countryDetailViewModel = hiltViewModel<CountryDetailViewModel, CountryDetailViewModel.CountryDetailFactory> { factory ->
                factory.create(name = countryName)
            }

            CountryDetailScreen(
                countryDetailViewModel = countryDetailViewModel,
                backClick = {
                    navController.popBackStack()
                }
            )
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
            route = Screen.FavoritePage.route + "/{${FAVORITE}}",
            arguments = listOf(
                navArgument(name = FAVORITE) {
                    type = NavType.StringType
                })
        ) {
            navController.currentBackStackEntry?.savedStateHandle?.set(
                Screen.FavoritePage.route,
                FAVORITE
            )
            FavoriteScreen(
                backClick = {
                    navController.popBackStack()
                },
                onClickFavorite = { countryName ->
                    //navController.currentBackStackEntry?.savedStateHandle?.set(
                    //    Screen.CountryDetailPage.route,
                    //    countryDetailItem
                    //)
                    navController.navigate(route = Screen.CountryDetailPage.route + "/$countryName")
                }
            )
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
            }, clickCountry = { countryName ->
                navController.navigate(route = Screen.CountryDetailPage.route+"/$countryName")
            })
        }

        //QUIZ PAGESS

        composable(route = Screen.PlayQuizPage.route) {
            PlayQuiz(
                backClick = {
                    navController.navigate(Screen.HomePage.route)
                },
                clickHomeItem = { quizLevel ->
                    navController.navigate(route = Screen.SharedDifficultyPage.route + "/${quizLevel}")
                    if (quizLevel == Constants.EUROPE) {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            DIFFICULT,
                            Constants.EUROPE
                        )
                        navController.navigate(route = Screen.QuizDetailPage.route)
                    } else if (quizLevel == Constants.AMERICA) {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            DIFFICULT,
                            Constants.AMERICA
                        )
                        navController.navigate(route = Screen.QuizDetailPage.route)
                    } else if (quizLevel == Constants.AFRICA) {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            DIFFICULT,
                            Constants.AFRICA
                        )
                        navController.navigate(route = Screen.QuizDetailPage.route)
                    } else if (quizLevel == Constants.ASIA) {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            DIFFICULT,
                            Constants.ASIA
                        )
                        navController.navigate(route = Screen.QuizDetailPage.route)
                    } else if (quizLevel == Constants.OCEANIA) {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            DIFFICULT,
                            Constants.OCEANIA
                        )
                        navController.navigate(route = Screen.QuizDetailPage.route)
                    } else {
                        navController.navigate(route = Screen.SharedDifficultyPage.route + "/${quizLevel}")
                    }
                })
        }
        composable(route = Screen.QuizDetailPage.route) {
            QuizPage(
                backClick = {
                    if (it?.isNotEmpty() == true) {
                        //bunu ekleme sebebim regionlardan geriye tıklayınca zorluk sayfasına yönelmemeli.
                        navController.navigate(Screen.PlayQuizPage.route)
                    } else {
                        navController.popBackStack()
                    }
                },
                navController = navController
            )
        }

        composable(
            route = "${Screen.SharedDifficultyPage.route}/{${DIFFICULT}}",
            arguments = listOf(
                navArgument(name = DIFFICULT) {
                    type = NavType.StringType
                })
        )
        { difficultLevel ->
            difficultLevel.arguments?.getString(DIFFICULT)?.let {
                SharedDifficultyScreen(
                    difficultLevel = it,
                    backClick = {
                        navController.navigate(Screen.PlayQuizPage.route)
                        navController.currentBackStackEntry?.savedStateHandle?.remove<String>(
                            DIFFICULT
                        )
                    },
                    chooseCategoryData = { quizItem, diffLevel ->
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            Screen.QuizDetailPage.route,
                            quizItem
                        )

                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            DIFFICULT,
                            diffLevel
                        )

                        navController.navigate(route = Screen.QuizDetailPage.route)
                    })
            }
        }
    }
}

val NavHostController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED