package com.mckstudio.countriesapp.data.repositoryimpl

import com.mckstudio.countriesapp.data.local_db.CountryDao
import com.mckstudio.countriesapp.data.response.Name
import com.mckstudio.countriesapp.data.response.Response
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.domain.repository.FavoriteCountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException

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
            } catch (e: SocketTimeoutException) {
                trySend(Response.Error("Timeout.Try Again"))
            } catch (e: HttpException) {
                trySend(Response.Error("Check your internet connection.."))
            } catch (e: IOException) {
                trySend(Response.Error("Couldn't reach server.Check your internet connection.."))
            }catch (e: Exception) {
                trySend(Response.Error("An unexpected error occured"))
            }
        }

    override suspend fun deleteCountry(countryDetailItem: CountryDetailItem): Flow<Response<Unit>> =
        flow {
            try {
                emit(Response.Loading())
                val deleteCountry = countryDao.deleteCountry(countryDetailItem = countryDetailItem)
                emit(Response.Success(deleteCountry))
            } catch (e: SocketTimeoutException) {
                emit(Response.Error("Timeout.Try Again"))
            } catch (e: HttpException) {
                emit(Response.Error("Check your internet connection.."))
            } catch (e: IOException) {
                emit(Response.Error("Couldn't reach server.Check your internet connection.."))
            }catch (e: Exception) {
                emit(Response.Error("An unexpected error occured"))
            }
        }

    override suspend fun insertCountry(countryDetailItem: CountryDetailItem): Flow<Response<Unit>> =
        flow {
            try {
                emit(Response.Loading())
                countryDao.insertCountry(countryDetailItem = countryDetailItem)
                emit(Response.Success(Unit))
            }catch (e: SocketTimeoutException) {
                emit(Response.Error("Timeout.Try Again"))
            } catch (e: HttpException) {
                emit(Response.Error("Check your internet connection.."))
            } catch (e: IOException) {
                emit(Response.Error("Couldn't reach server.Check your internet connection.."))
            }catch (e: Exception) {
                emit(Response.Error("An unexpected error occured"))
            }
        }

    override suspend fun checkExistCountry(name: Name): Flow<Response<Int>> =
        flow {
            try {
                emit(Response.Loading())
                val insertCountry = countryDao.checkExistCountry(name = name)
                emit(Response.Success(insertCountry))
            }catch (e: SocketTimeoutException) {
                emit(Response.Error("Timeout.Try Again"))
            } catch (e: HttpException) {
                emit(Response.Error("Check your internet connection.."))
            } catch (e: IOException) {
                emit(Response.Error("Couldn't reach server.Check your internet connection.."))
            }catch (e: Exception) {
                emit(Response.Error("An unexpected error occured"))
            }
        }
}