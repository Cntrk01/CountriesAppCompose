package com.mckstudio.countriesapp.domain.newmodel

import com.mckstudio.countriesapp.domain.model.BaseTranslation

data class CountryDetail(
    val name : String,
    val officialName : String,
    val commonName: String,
    val capital: String,
    val currency: String,
    val flagUrl: String,
    val population: String,
    val languages: String,
    val region: String,
    val subregion: String,
    val coatOfArmsUrl: String,
    val translations: List<BaseTranslation>,
    val googleMaps: String,
    val status: String,
)