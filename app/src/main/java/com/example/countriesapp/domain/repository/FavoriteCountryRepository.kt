package com.example.countriesapp.domain.repository

import com.example.countriesapp.data.response.Name
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.CountryDetailItem
import kotlinx.coroutines.flow.Flow

interface FavoriteCountryRepository {
    fun getAllCountry () : Flow<Response<List<CountryDetailItem>>>
    suspend fun deleteCountry(countryDetailItem: CountryDetailItem) : Flow<Response<Unit>>
    suspend fun insertCountry(countryDetailItem: CountryDetailItem) : Flow<Response<Unit>>
    suspend fun checkExistCountry(name: Name) : Flow<Response<Int>>
}