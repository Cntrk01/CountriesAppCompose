package com.example.countriesapp.navigation

sealed class Screen(val route:String){
    //Home Page
    object HomePage : Screen(route = "home_page")
    object RegionPage: Screen(route = "screen_page")
    object SubRegionPage : Screen(route = "sub_region")
    object CurrencyPage : Screen(route = "currencies")
    object PlayQuizPage : Screen(route = "play_quiz")
    object FavoritePage : Screen(route = "favorite")

    object RegionCountryListPage : Screen(route = "region_list_page")
    object CountryPage : Screen(route = "country_page")
    object CountryDetailPage : Screen(route = "country_detail_page")

    //QUIZ

    object SharedDifficultyPage : Screen(route = "shared_difficulty_page")
    object QuizDetailPage : Screen(route = "quiz_detail_page")
}
