package com.example.countriesapp.data.response

import com.example.countriesapp.R
import com.example.countriesapp.presentation.country_detail.screen.BaseTranslation

data class Translations(
    val ara: Ara?,
    val bre: Bre?,
    val ces: Ces?,
    val cym: Cym?,
    val deu: Deu?,
    val est: Est?,
    val fin: Fin?,
    val fra: Fra?,
    val hrv: Hrv?,
    val hun: Hun?,
    val ita: İta?,
    val jpn: Jpn?,
    val kor: Kor?,
    val nld: Nld?,
    val per: Per?,
    val pol: Pol?,
    val por: Por?,
    val rus: Rus?,
    val slk: Slk?,
    val spa: Spa?,
    val srp: Srp?,
    val swe: Swe?,
    val tur: Tur?,
    val urd: Urd?,
    val zho: Zho?
)
fun Translations.toList():List<BaseTranslation> {
    return listOf(
        BaseTranslation(firstName = "tur", common = tur?.common, official = tur?.official),
        BaseTranslation(firstName = "ara", common = ara?.common, official = ara?.official),
        BaseTranslation(firstName = "bre", common = bre?.common, official = bre?.official),
        BaseTranslation(firstName = "ces", common = ces?.common, official = ces?.official),
        BaseTranslation(firstName = "cym", common = cym?.common, official = cym?.official),
        BaseTranslation(firstName = "deu", common = deu?.common, official = deu?.official),
        BaseTranslation(firstName = "est", common = est?.common, official = est?.official),
        BaseTranslation(firstName = "fin", common = fin?.common, official = fin?.official),
        BaseTranslation(firstName = "fra", common = fra?.f, official = fra?.m),
        BaseTranslation(firstName = "hrv", common = hrv?.common, official = hrv?.official),
        BaseTranslation(firstName = "hun", common = hun?.common, official = hun?.official),
        BaseTranslation(firstName = "ita", common = ita?.common, official = ita?.official),
        BaseTranslation(firstName = "jpn", common = jpn?.common, official = jpn?.official),
        BaseTranslation(firstName = "kor", common = kor?.common, official = kor?.official),
        BaseTranslation(firstName = "nld", common = nld?.common, official = nld?.official),
        BaseTranslation(firstName = "per", common = per?.common, official = per?.official),
        BaseTranslation(firstName = "pol", common = pol?.common, official = pol?.official),
        BaseTranslation(firstName = "por", common = por?.common, official = por?.official),
        BaseTranslation(firstName = "rus", common = rus?.common, official = rus?.official),
        BaseTranslation(firstName = "slk", common = slk?.common, official = slk?.official),
        BaseTranslation(firstName = "spa", common = spa?.common, official = spa?.official),
        BaseTranslation(firstName = "srp", common = srp?.common, official = srp?.official),
        BaseTranslation(firstName = "swe", common = swe?.common, official = swe?.official),
        BaseTranslation(firstName = "urd", common = urd?.common, official = urd?.official),
        BaseTranslation(firstName = "zho", common = zho?.common, official = zho?.official)
    )
}

