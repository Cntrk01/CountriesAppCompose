package com.example.countriesapp.presentation.country_list.state

import com.example.countriesapp.domain.model.CountryItem

data class CountryListState(
    val loading : Boolean=false,
    val error : String="",
    var countryData : List<CountryItem> ?= emptyList()
)
