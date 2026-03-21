package com.mckstudio.countriesapp.domain.model

import android.os.Parcelable
import com.mckstudio.countriesapp.data.model.CapitalInfo
import com.mckstudio.countriesapp.data.model.CoatOfArms
import com.mckstudio.countriesapp.data.model.Currency
import com.mckstudio.countriesapp.data.model.Flags
import com.mckstudio.countriesapp.data.model.Maps
import com.mckstudio.countriesapp.data.model.Name
import com.mckstudio.countriesapp.data.model.PostalCode
import com.mckstudio.countriesapp.data.model.Translations
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CountryRoomItem(
    val id : Int ?=0,
    val capitalInfo: @RawValue CapitalInfo?,
    val flags:@RawValue Flags?,
    val maps:@RawValue Maps?,
    val name:@RawValue Name?,
    val population: Int?,
    val postalCode:@RawValue PostalCode?,
    val region: String?,
    val status: String?,
    val subregion: String?,
    val timezones: List<String>?,
    val translations:@RawValue Translations?,
    val coatOfArms: @RawValue CoatOfArms?,
    val currencies: @RawValue Map<String, Currency>?,
    val latlng: List<Double>?
) : Parcelable
