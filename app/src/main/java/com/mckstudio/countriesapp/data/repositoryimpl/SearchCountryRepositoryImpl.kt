package com.mckstudio.countriesapp.data.repositoryimpl

import com.mckstudio.countriesapp.data.remote.CountryApi
import com.mckstudio.countriesapp.data.response.Response
import com.mckstudio.countriesapp.data.response.toCountryItem
import com.mckstudio.countriesapp.domain.model.CountryItem
import com.mckstudio.countriesapp.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException

class SearchCountryRepositoryImpl (private val countryApi: CountryApi): SearchRepository {

    override suspend fun searchRepository(countryName: String): Flow<Response<List<CountryItem>>> {
        return flow {
            try {
                emit(Response.Loading())
                val data=countryApi.getCountryWithName(name = countryName).map { it.toCountryItem() }
                emit(Response.Success(data = data))
            }
            catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        when (e.code()) {
                            404 -> emit(Response.Error("Country not found"))
                            500 -> emit(Response.Error("Server error"))
                            else -> emit(Response.Error("An unexpected error occurred"))
                        }
                    }
                    is SocketTimeoutException -> emit(Response.Error("Timeout. Try Again"))
                    is IOException -> emit(Response.Error("Couldn't reach server. Check your internet connection"))
                    else -> emit(Response.Error("An unexpected error occurred"))
                }
            }
        }
    }
}