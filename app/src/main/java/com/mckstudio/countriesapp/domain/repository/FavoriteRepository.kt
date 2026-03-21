package com.mckstudio.countriesapp.domain.repository

import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.domain.newmodel.CountryDetailEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllCountry () : Flow<Response<List<CountryDetailEntity>>>
    suspend fun deleteCountry(countryName: String) : Flow<Response<Unit>>
    suspend fun insertCountry(countryDetailItem: CountryDetailEntity) : Flow<Response<Unit>>
    suspend fun getFavoriteByName(name: String) : Flow<Response<CountryDetailEntity?>>
    suspend fun performMigrationIfNeeded()
}