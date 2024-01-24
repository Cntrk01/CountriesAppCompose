package com.example.countriesapp.domain.repository

import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.CountryDetailItem
import com.example.countriesapp.domain.model.CountryItem
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    suspend fun getAllCountry(page:Int) : Flow<Response<List<CountryItem>>>

    suspend fun getCountryWithName (name:String) : Flow<Response<List<CountryDetailItem>>>
}