package com.example.countriesapp.presentation.favorite.state

import com.example.countriesapp.domain.model.CountryDetailItem


data class FavoriteState(
    val loading : Boolean ?=false,
    val error : String = "",
    val favoriteList : List<CountryDetailItem> ?= emptyList(),
    val countryDeleted : Boolean ?=false,
    val checkExists : Int ?= 0
)
