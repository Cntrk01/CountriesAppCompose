package com.example.countriesapp.navigation

sealed class Screen(val route:String){
    object HomePage : Screen(route = "home_page")
    object RegionPage: Screen(route = "screen_page")
    object SubRegionPage : Screen(route = "sub_region")
    object RegionCountryListPage : Screen(route = "region_list_page")
    object CountryPage : Screen(route = "country_page")
    object CountryDetailPage : Screen(route = "country_detail_page")
}
