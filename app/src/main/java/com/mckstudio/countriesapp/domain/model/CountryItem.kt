package com.mckstudio.countriesapp.domain.model

import com.mckstudio.countriesapp.data.response.Flags
import com.mckstudio.countriesapp.domain.model.CountryDetailItem

data class CountryItem(
    val flag: Flags?,
    val name:String?,
    val countryDetailItem: CountryDetailItem
)
