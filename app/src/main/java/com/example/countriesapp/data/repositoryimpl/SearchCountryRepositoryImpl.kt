package com.example.countriesapp.data.repositoryimpl

import com.example.countriesapp.data.remote.CountryApi
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.data.response.toCountryItem
import com.example.countriesapp.domain.model.CountryItem
import com.example.countriesapp.domain.repository.SearchRepository
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
}