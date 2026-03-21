package com.mckstudio.countriesapp.data.mapper

import com.mckstudio.countriesapp.data.model.countrydetail.DetailResponseDTO
import com.mckstudio.countriesapp.data.model.toBaseTranslations
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.domain.model.CountryItem
import com.mckstudio.countriesapp.domain.newmodel.CountryDetail
import com.mckstudio.countriesapp.domain.newmodel.CountryDetailEntity
import java.util.Locale

fun DetailResponseDTO.toCountryDetail(): CountryDetail {
    return CountryDetail(
        name = this.name?.common ?: "Unknown",
        officialName = this.name?.official ?: "",
        capital = this.capital?.firstOrNull() ?: "-",
        flagUrl = this.flags?.png ?: "",
        coatOfArmsUrl = this.coatOfArms?.png ?: "",
        currency = this.currencies?.values?.firstOrNull().let {
            val currencyName = it?.name?.replaceFirstChar { char ->
                if (char.isLowerCase()) char.titlecase(Locale.ROOT) else char.toString()
            }  ?: "N/A"
            "$currencyName (${it?.symbol ?: ""})"
        } ,
        population = String.format("%, d", this.population ?: 0),
        region = this.region ?: "",
        subregion = this.subregion ?: "",
        languages = this.languages?.cat.toString(),
        translations = this.translations?.toBaseTranslations() ?: emptyList(),
        commonName = this.name?.common ?: "",
        googleMaps = this.maps?.googleMaps ?: "",
        status = this.status ?: "",
    )
}



fun CountryDetailEntity.toCountryDetailFromFavoriteScreen() : CountryItem {
    return CountryItem(
        name = this.name,
        flag = this.flagUrl,
        capital = this.capital,
        currency = this.currency,
    )
}

fun CountryDetailEntity.dbToDetail(): CountryDetail {
    return CountryDetail(
        name = this.name,
        officialName = this.officialName,
        commonName = this.commonName,
        capital = this.capital,
        currency = this.currency,
        flagUrl = this.flagUrl,
        population = this.population,
        languages = this.languages,
        region = this.region,
        subregion = this.subregion,
        coatOfArmsUrl = this.coatOfArmsUrl,
        translations = this.translations,
        googleMaps = this.googleMaps,
        status = this.status
    )
}

fun CountryDetail.toCreateFavorite(): CountryDetailEntity {
    return CountryDetailEntity(
        name = this.name,
        officialName = this.officialName,
        commonName = this.commonName,
        capital = this.capital,
        currency = this.currency,
        flagUrl = this.flagUrl,
        population = this.population,
        languages = this.languages,
        region = this.region,
        subregion = this.subregion,
        coatOfArmsUrl = this.coatOfArmsUrl,
        translations = this.translations,
        googleMaps = this.googleMaps,
        status = this.status,
    )
}

fun CountryDetailItem.toNewEntity(): CountryDetailEntity {
    return CountryDetailEntity(
        commonName = this.name?.common ?: "Unknown",
        officialName = this.name?.official ?: "",
        name = this.name?.common ?: "Unknown",
        capital = this.capitalInfo.toString(),
        currency = this.currencies?.values?.firstOrNull()?.let { "${it.name} (${it.symbol})" } ?: "N/A",
        flagUrl = this.flags?.png ?: "",
        population = String.format("%, d", this.population ?: 0),
        region = this.region ?: "",
        subregion = this.subregion ?: "",
        languages = "N/A",
        coatOfArmsUrl = this.coatOfArms?.png ?: "",
        googleMaps = this.maps?.googleMaps ?: "",
        status = this.status ?: "",
        translations = this.translations?.toBaseTranslations() ?: emptyList()
    )
}
