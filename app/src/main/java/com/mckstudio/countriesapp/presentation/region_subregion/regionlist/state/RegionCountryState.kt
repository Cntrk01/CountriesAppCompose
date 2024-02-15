package com.mckstudio.countriesapp.presentation.region_subregion.regionlist.state

import com.mckstudio.countriesapp.domain.model.CountryItem

data class RegionCountryState(
    val loading : Boolean=false,
    val error : String="",
    var countryData : List<CountryItem> = emptyList()
)
