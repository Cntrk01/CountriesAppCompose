package com.mckstudio.countriesapp.presentation.country_detail.vm

import com.mckstudio.countriesapp.domain.newmodel.CountryDetail

data class CountryDetailState(
    val isLoading: Boolean = false,
    val data: CountryDetail ?= null,
    val error: String? = null,
    val isFavorite: Boolean = false,
)
