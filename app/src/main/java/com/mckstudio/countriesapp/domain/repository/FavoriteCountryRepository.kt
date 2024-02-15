package com.mckstudio.countriesapp.domain.repository

import com.mckstudio.countriesapp.data.response.Name
import com.mckstudio.countriesapp.data.response.Response
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import kotlinx.coroutines.flow.Flow

interface FavoriteCountryRepository {
    fun getAllCountry () : Flow<Response<List<CountryDetailItem>>>
    suspend fun deleteCountry(countryDetailItem: CountryDetailItem) : Flow<Response<Unit>>
    suspend fun insertCountry(countryDetailItem: CountryDetailItem) : Flow<Response<Unit>>
    suspend fun checkExistCountry(name: Name) : Flow<Response<Int>>
}