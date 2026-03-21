package com.mckstudio.countriesapp.data.model.countrymodel

import com.mckstudio.countriesapp.data.model.Flags

data class Name(
    val common: String,
    val capital : ArrayList<String>,
    val flags: Flags,
)