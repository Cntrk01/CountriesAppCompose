package com.mckstudio.countriesapp.navigation

sealed class Screen(val route:String){
    //Home Page
    data object HomePage : Screen(route = "home_page")
    data object RegionPage: Screen(route = "screen_page")
    data object SubRegionPage : Screen(route = "sub_region")
    data object CurrencyPage : Screen(route = "currencies")
    data object PlayQuizPage : Screen(route = "play_quiz")
    data object FavoritePage : Screen(route = "favorite")
    data object RegionCountryListPage : Screen(route = "region_list_page")
    data object CountryPage : Screen(route = "country_page")
    data object CountryDetailPage : Screen(route = "country_detail_page")

    //QUIZ
    data object SharedDifficultyPage : Screen(route = "shared_difficulty_page")
    data object QuizDetailPage : Screen(route = "quiz_detail_page")
}
