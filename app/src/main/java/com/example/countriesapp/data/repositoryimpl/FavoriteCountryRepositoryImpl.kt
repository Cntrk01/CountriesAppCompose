package com.example.countriesapp.data.repositoryimpl

import com.example.countriesapp.data.local_db.CountryDao
import com.example.countriesapp.data.response.Name
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.CountryDetailItem
import com.example.countriesapp.domain.repository.FavoriteCountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class FavoriteCountryRepositoryImpl(private val countryDao: CountryDao) :
    FavoriteCountryRepository {

    override fun getAllCountry(): Flow<Response<List<CountryDetailItem>>> =
        channelFlow {
            try {
                trySend(Response.Loading())
                val allCountryData = countryDao.getAllCountry()
                allCountryData.collectLatest {
                    trySend(Response.Success(it))
                }
            } catch (e: Exception) {
                trySend(Response.Error(e.localizedMessage ?: "Exception"))
            }
        }

    override suspend fun deleteCountry(countryDetailItem: CountryDetailItem): Flow<Response<Unit>> =
        flow {
            try {
                emit(Response.Loading())
                val deleteCountry = countryDao.deleteCountry(countryDetailItem = countryDetailItem)
                emit(Response.Success(deleteCountry))
            } catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "Exception"))
            }
        }

    override suspend fun insertCountry(countryDetailItem: CountryDetailItem): Flow<Response<Unit>> =
        flow {
            try {
                emit(Response.Loading())
                countryDao.insertCountry(countryDetailItem = countryDetailItem)
                emit(Response.Success(Unit))
            } catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "Exception"))
            }
        }

    override suspend fun checkExistCountry(name: Name): Flow<Response<Int>> =
        flow {
            try {
                emit(Response.Loading())
                val insertCountry = countryDao.checkExistCountry(name = name)
                emit(Response.Success(insertCountry))
            } catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "Exception"))
            }
        }
}