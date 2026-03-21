package com.mckstudio.countriesapp.presentation.favorite.state

import com.mckstudio.countriesapp.domain.model.CountryItem

data class FavoriteState(
    val loading : Boolean ?=false,
    val error : String = "",
    val favoriteList : List<CountryItem> ?= emptyList(),
    val countryDeleted : Boolean ?=false,
    val checkExists : Int ?= 0
)
