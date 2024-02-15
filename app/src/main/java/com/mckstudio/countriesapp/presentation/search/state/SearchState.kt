package com.mckstudio.countriesapp.presentation.search.state

import com.mckstudio.countriesapp.domain.model.CountryItem

data class SearchState (
    val error : String ="",
    val loading : Boolean = false,
    val searchCountry : List<CountryItem> ?= emptyList()
)