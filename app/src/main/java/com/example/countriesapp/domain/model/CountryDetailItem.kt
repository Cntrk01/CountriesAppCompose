package com.example.countriesapp.domain.model

import com.example.countriesapp.data.response.CapitalInfo
import com.example.countriesapp.data.response.Flags
import com.example.countriesapp.data.response.Maps
import com.example.countriesapp.data.response.Name
import com.example.countriesapp.data.response.PostalCode
import com.example.countriesapp.data.response.Translations

data class CountryDetailItem(
    val capitalInfo: CapitalInfo?,
    val flags: Flags?,
    val maps: Maps?,
    val name: Name?,
    val population: Int?,
    val postalCode: PostalCode?,
    val region: String?,
    val status: String?,
    val subregion: String?,
    val timezones: List<String>?,
    val translations: Translations?,
)
