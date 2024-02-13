package com.example.countriesapp.data.repositoryimpl

import com.example.countriesapp.data.remote.CountryApi
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.data.response.toCountryDetailItem
import com.example.countriesapp.data.response.toCountryItem
import com.example.countriesapp.domain.model.CountryDetailItem
import com.example.countriesapp.domain.model.CountryItem
import com.example.countriesapp.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val countryApi: CountryApi) :
    CountryRepository {

    override suspend fun getAllCountry(page: Int): Flow<Response<List<CountryItem>>> = flow {
        try {
            emit(Response.Loading())
            val startingIndex = page * 20
            val data = countryApi.getAllCountry().map {
                it.toCountryItem()
            }
            val filteredData = data.subList(startingIndex - 20, startingIndex)
            emit(Response.Success(data = filteredData))
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

    override suspend fun getCountryWithName(name: String): Flow<Response<List<CountryDetailItem>>> =
        flow {
            try {
                emit(Response.Loading())
                val data = countryApi.getCountryWithName(name = name).map {
                    it.toCountryDetailItem()
                }
                emit(Response.Success(data = data))
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

    override suspend fun getCountryWithRegion(
        regionName: String
    ): Flow<Response<List<CountryItem>>> = flow {
        try {
            emit(Response.Loading())
            val data = countryApi.getCountryWithRegion(region = regionName).map {
                it.toCountryItem()
            }
            emit(Response.Success(data = data))
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