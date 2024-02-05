package com.example.countriesapp.domain.use_case

import com.example.countriesapp.data.repositoryimpl.FavoriteCountryRepositoryImpl
import com.example.countriesapp.data.response.Name
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.CountryRoomItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteCountryUseCase @Inject constructor(private val favoriteCountryRepositoryImpl: FavoriteCountryRepositoryImpl) {

    fun getAllCountry(): Flow<Response<List<CountryRoomItem>>> = favoriteCountryRepositoryImpl.getAllCountry()

    suspend fun deleteCountry(countryDetailItem: CountryRoomItem): Flow<Response<Unit>> = favoriteCountryRepositoryImpl.deleteCountry(countryDetailItem = countryDetailItem)

    suspend fun insertCountry(countryDetailItem: CountryRoomItem): Flow<Response<Unit>> = favoriteCountryRepositoryImpl.insertCountry(countryDetailItem = countryDetailItem)

    suspend fun checkExistCountry(name: Name): Flow<Response<Int>> = favoriteCountryRepositoryImpl.checkExistCountry(name = name)
}