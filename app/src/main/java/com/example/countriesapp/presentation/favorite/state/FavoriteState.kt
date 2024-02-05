package com.example.countriesapp.presentation.favorite.state

import com.example.countriesapp.domain.model.CountryRoomItem

data class FavoriteState(
    val loading : Boolean ?=false,
    val error : String = "",
    val favoriteList : List<CountryRoomItem> ?= emptyList(),
    val countryAdded : Boolean ?= false,
    val countryDeleted : Boolean ?=false,
    val checkExists : Int ?= 0
)
