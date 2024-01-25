package com.example.countriesapp.domain.model

import com.example.countriesapp.data.response.Flags

data class CountryItem(
    val flag:Flags?,
    val name:String?,
    val countryDetailItem: CountryDetailItem
)
