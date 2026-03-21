package com.mckstudio.countriesapp.data.model.countrydetail

import com.mckstudio.countriesapp.data.model.CapitalInfo
import com.mckstudio.countriesapp.data.model.CoatOfArms
import com.mckstudio.countriesapp.data.model.Currency
import com.mckstudio.countriesapp.data.model.Flags
import com.mckstudio.countriesapp.data.model.Languages
import com.mckstudio.countriesapp.data.model.Maps
import com.mckstudio.countriesapp.data.model.Name
import com.mckstudio.countriesapp.data.model.Translations

class DetailResponse : ArrayList<DetailResponseDTO>()

data class DetailResponseDTO(
    val name: Name?,
    val capital: List<String>?,
    val flags: Flags?,
    val coatOfArms: CoatOfArms?,
    val capitalInfo: CapitalInfo?,
    val translations: Translations?,
    val currencies: Map<String, Currency>?,
    val population: Int?,
    val region: String?,
    val subregion: String?,
    val languages: Languages?,
    val status : String?,
    val maps : Maps?,
)
