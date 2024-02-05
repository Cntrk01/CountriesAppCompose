package com.example.countriesapp.domain.repository

import com.example.countriesapp.data.response.Name
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.CountryRoomItem
import kotlinx.coroutines.flow.Flow

interface FavoriteCountryRepository {
    fun getAllCountry () : Flow<Response<List<CountryRoomItem>>>
    suspend fun deleteCountry(countryDetailItem: CountryRoomItem) : Flow<Response<Unit>>
    suspend fun insertCountry(countryDetailItem: CountryRoomItem) : Flow<Response<Unit>>
    suspend fun checkExistCountry(name: Name) : Flow<Response<Int>>
}