package com.mckstudio.countriesapp.common

object Constants {
    //API
    const val BASE_URL="https://restcountries.com/v3.1/"

    //HOME SCREEN
    const val Country_Title = "All Country"
    const val Country_Subtitle = "250+ nations"
    const val Region_Title = "Region"
    const val Region_Subtitle = "Explore continents"
    const val Region_Name = "Region Name"
    const val Sub_Region_Title = "Sub Region"
    const val Sub_Region_Subtitle = "Detailed areas"
    const val Currency_Title = "Currency"
    const val Currency_Subtitle = "Money & rates"
    const val Quiz_Title = "Quiz"
    const val Quiz_Subtitle = "Test your knowledge"
    const val Favorite_Title = "Favorite"
    const val Favorite_Subtitle = "Saved places"

    //REGION
    const val ANTARCTIC = "Antarctic"
    const val EUROPE = "Europe"
    const val AMERICA = "America"
    const val AMERICAS = "Americas"
    const val AFRICA = "Africa"
    const val OCEANIA = "Oceania"
    const val ASIA = "Asia"

    //SUBREGION
    const val SOUTHERN_EUROPE = "Southern Europe"
    const val SOUTH_EASTERN_ASIA = "South-Eastern Asia"
    const val North_America = "North America"
    const val Melanesia = "Melanesia"
    const val Central_Europe = "Central Europe"
    const val Eastern_Africa = "Eastern Africa"
    const val Western_Africa = "Western Africa"
    const val Northern_Africa = "Northern Africa"
    const val Southern_Africa = "Southern Africa"
    const val Northern_Europe = "Northern Europe"
    const val Caribbean = "Caribbean"
    const val South_America = "South America"
    const val Southeast_Europe = "Southeast Europe"
    const val Middle_Africa = "Middle Africa"
    const val Southern_Asia = "Southern Asia"
    const val Eastern_Asia = "Eastern Asia"

    val regions = listOf(
        SOUTHERN_EUROPE,
        SOUTH_EASTERN_ASIA,
        North_America,
        Melanesia,
        Central_Europe,
        Eastern_Africa,
        Western_Africa,
        Northern_Africa,
        Southern_Africa,
        Northern_Europe,
        Caribbean,
        South_America,
        Southeast_Europe,
        Middle_Africa,
        Southern_Asia,
        Eastern_Asia
    )

    // LEVEL
    const val EASY = "Easy"
    const val MEDIUM = "Medium"
    const val HARD = "Hard"
    const val EXPERT = "Expert"
    const val COUNTRY = "Country"

    // ARGUMENTS
    const val DIFFICULT = "difficulty"
    const val FAVORITE = "Favorite"

    const val Flag = "Flag"
    const val Capital = "Capital"
    const val Emblems = "Emblems"
}