package com.example.countriesapp.navigation

sealed class Screen(val route:String){
    object CountryPage : Screen(route = "country_page")
    object CountryDetailPage : Screen(route = "country_detail_page")
}
