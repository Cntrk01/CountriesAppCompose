package com.example.countriesapp.presentation.region_subregion.regionlist.state

import com.example.countriesapp.domain.model.CountryItem

data class RegionCountryState(
    val loading : Boolean=false,
    val error : String="",
    var countryData : List<CountryItem> = emptyList()
)
