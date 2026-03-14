package com.mckstudio.countriesapp.domain.model

import com.mckstudio.countriesapp.components.CountryList
import com.mckstudio.countriesapp.data.response.Flags

data class CountryItem(
    val flag: Flags?,
    val name:String?,
    val capital: String?,
    val countryDetailItem: CountryDetailItem
)

fun List<CountryItem>.toCountryDetailItem(): List<CountryList> {
    return this.map { country ->
        CountryList(
            title = country.name ?: "",
            subtitle = country.capital ?: "",
            imageUrl = country.flag?.png ?: "",
            countryDetailItem = country.countryDetailItem,
        )
    }
}

