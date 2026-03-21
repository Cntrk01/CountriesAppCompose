package com.mckstudio.countriesapp.domain.model

import com.mckstudio.countriesapp.components.CountryList
import com.mckstudio.countriesapp.data.model.Flags
import com.mckstudio.countriesapp.domain.newmodel.CountryCurrency

data class CountryItem(
    val flag: String?,
    val name:String?,
    val capital: String?,
    val currency : String?,
)

fun List<CountryItem>.toCountryDetailItem(): List<CountryList> {
    return this.map { country ->
        CountryList(
            title = country.name ?: "",
            subtitle = country.capital ?: "",
            imageUrl = country.flag ?: "",
        )
    }
}

