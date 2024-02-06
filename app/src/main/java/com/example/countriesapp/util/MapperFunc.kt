package com.example.countriesapp.util

import androidx.room.PrimaryKey
import com.example.countriesapp.R
import com.example.countriesapp.domain.model.CountryDetailItem
import com.example.countriesapp.domain.model.CountryRoomItem
import java.util.Locale

fun CreateFirstNameToIconMap(checkCountry:String): Int {
    return when(checkCountry.lowercase(Locale.ROOT)){
        "tur" -> R.drawable.icons_turkey
        "ara" -> R.drawable.icons_arabic
        "bre" -> R.drawable.icons_brazil
        "ces" -> R.drawable.icons_czech
        "cym" -> R.drawable.icons_cym
        "deu" -> R.drawable.icons_germany
        "est" -> R.drawable.icons_estonia
        "fin" -> R.drawable.icons_finland
        "fra" -> R.drawable.icons_france
        "hrv" -> R.drawable.icons_croatia
        "hun" -> R.drawable.icons_hungary
        "ita" -> R.drawable.icons_italy
        "jpn" -> R.drawable.icons_japan
        "kor" -> R.drawable.icons_north_korea
        "nld" -> R.drawable.icons_netherlands
        "per" -> R.drawable.icons_peru
        "pol" -> R.drawable.icons_poland
        "por" -> R.drawable.icons_portugal
        "rus" -> R.drawable.icons_russia
        "slk" -> R.drawable.icons_slovakia
        "spa" -> R.drawable.icons_spain
        "srp" -> R.drawable.icons_serbia
        "swe" -> R.drawable.icons_sweden
        "urd" -> R.drawable.icons_jordan
        "zho" -> R.drawable.icons_china

        else-> R.drawable.icons_turkey
    }
}

fun CountryDetailItem.mapToRoomItem(countryDetailItem: CountryDetailItem): CountryRoomItem {
    return CountryRoomItem(
        capitalInfo = countryDetailItem.capitalInfo,
        flags = countryDetailItem.flags,
        maps = countryDetailItem.maps,
        name = countryDetailItem.name,
        population = countryDetailItem.population,
        postalCode = countryDetailItem.postalCode,
        region = countryDetailItem.region,
        status = countryDetailItem.status,
        subregion = countryDetailItem.subregion,
        timezones = countryDetailItem.timezones,
        translations = countryDetailItem.translations,
        coatOfArms = countryDetailItem.coatOfArms,
        currencies = countryDetailItem.currencies,
        latlng = countryDetailItem.latlng
    )
}

fun CountryRoomItem.mapToDetailItem(countryRoomItem: CountryRoomItem): CountryDetailItem {
    return CountryDetailItem(
        capitalInfo = countryRoomItem.capitalInfo,
        flags = countryRoomItem.flags,
        maps = countryRoomItem.maps,
        name = countryRoomItem.name,
        population = countryRoomItem.population,
        postalCode = countryRoomItem.postalCode,
        region = countryRoomItem.region,
        status = countryRoomItem.status,
        subregion = countryRoomItem.subregion,
        timezones = countryRoomItem.timezones,
        translations = countryRoomItem.translations,
        coatOfArms = countryRoomItem.coatOfArms,
        currencies = countryRoomItem.currencies,
        latlng = countryRoomItem.latlng
    )
}