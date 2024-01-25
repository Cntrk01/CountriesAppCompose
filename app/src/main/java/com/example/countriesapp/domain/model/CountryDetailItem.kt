package com.example.countriesapp.domain.model

import android.os.Parcelable
import com.example.countriesapp.data.response.CapitalInfo
import com.example.countriesapp.data.response.CoatOfArms
import com.example.countriesapp.data.response.Currency
import com.example.countriesapp.data.response.Flags
import com.example.countriesapp.data.response.Maps
import com.example.countriesapp.data.response.Name
import com.example.countriesapp.data.response.PostalCode
import com.example.countriesapp.data.response.Translations
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CountryDetailItem(
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
