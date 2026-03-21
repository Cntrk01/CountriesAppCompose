package com.mckstudio.countriesapp.domain.newmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mckstudio.countriesapp.domain.model.BaseTranslation

@Entity(tableName = "favorite_countries")
data class CountryDetailEntity(
    @PrimaryKey
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
