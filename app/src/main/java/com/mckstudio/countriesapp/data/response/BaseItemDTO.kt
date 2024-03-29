package com.mckstudio.countriesapp.data.response

import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.domain.model.CountryItem
import com.mckstudio.countriesapp.domain.model.QuizItem

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
    val idd: İdd?,
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
    return CountryItem(flag = flags, name = name?.common, countryDetailItem = toCountryDetailItem())
}

fun BaseItemDTO.toCountryDetailItem(): CountryDetailItem {
    return CountryDetailItem(
        capitalInfo = capitalInfo,
        flags = flags,
        maps = maps,
        name=name,
        population=population,
        postalCode=postalCode,
        region=region,
        status=status,
        subregion=subregion,
        timezones=timezones,
        translations=translations,
        coatOfArms = coatOfArms,
        currencies = currencies,
        latlng = latlng
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



