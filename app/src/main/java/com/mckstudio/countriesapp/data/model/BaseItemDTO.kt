package com.mckstudio.countriesapp.data.model

import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.domain.model.CountryItem
import com.mckstudio.countriesapp.domain.model.QuizItem
import com.mckstudio.countriesapp.domain.model.RecommendedItem

data class BaseItemDTO(
    val altSpellings: List<String>?,
    val area: Double?,
    val borders: List<String>?,
    val capital: List<String>?,
    val capitalInfo: CapitalInfo?,
    val car: Car?,
    val cca2: String?,
    val cca3: String?,
    val ccn3: String?,
    val cioc: String?,
    val coatOfArms: CoatOfArms?,
    val continents: List<String>?,
    val currencies: Map<String, Currency>?,
    val demonyms: Demonyms?,
    val fifa: String?,
    val flag: String?,
    val flags: Flags?,
    val idd: Idd?,
    val independent: Boolean?,
    val landlocked: Boolean?,
    val languages: Languages?,
    val latlng: List<Double>?,
    val maps: Maps?,
    val name: Name?,
    val population: Int?,
    val postalCode: PostalCode?,
    val region: String?,
    val startOfWeek: String?,
    val status: String?,
    val subregion: String?,
    val timezones: List<String>?,
    val tld: List<String>?,
    val translations: Translations?,
    val unMember: Boolean?
)

fun BaseItemDTO.toCountryItem(): CountryItem {
    return CountryItem(
        flag = flags?.png.toString(),
        name = name?.common,
        capital = capital?.first() ,
        currency = currencies?.values?.firstOrNull()?.name ?: "N/A",
    )
}

fun BaseItemDTO.toRecommendedItem(): RecommendedItem {
    return RecommendedItem(
        countryImage = flags?.png,
        countryName = name?.common ?: "",
        countryCapital = capital?.first() ?: "",
        countryRegion = region ?: ""
    )
}

fun BaseItemDTO.toQuizItem() : QuizItem {
    return QuizItem(flag = flags?.png, name = name?.common)
}

fun BaseItemDTO.toQuizItemCapital() : QuizItem {
    return QuizItem(flag = flags?.png , name=capital?.first())
}

fun BaseItemDTO.toQuizItemEmblems() : QuizItem {
    return QuizItem(flag = coatOfArms?.png , name=name?.common)
}



