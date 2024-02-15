package com.mckstudio.countriesapp.presentation.country_list.state

import com.mckstudio.countriesapp.domain.model.CountryItem

data class CountryListState(
    val loading : Boolean = false,
    val error : String = "",
    var countryData : List<CountryItem> = emptyList()
)
