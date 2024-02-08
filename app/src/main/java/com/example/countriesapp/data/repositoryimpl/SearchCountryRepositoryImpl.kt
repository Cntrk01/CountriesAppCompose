package com.example.countriesapp.data.repositoryimpl

import com.example.countriesapp.data.remote.CountryApi
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.data.response.toCountryItem
import com.example.countriesapp.domain.model.CountryItem
import com.example.countriesapp.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class SearchCountryRepositoryImpl (private val countryApi: CountryApi): SearchRepository {

    override suspend fun searchRepository(countryName: String): Flow<Response<List<CountryItem>>> {
        return flow {
            try {
                emit(Response.Loading())
                val data=countryApi.getCountryWithName(name = countryName).map { it.toCountryItem() }
                emit(Response.Success(data = data))
            }catch (e:Exception){
                emit(Response.Error(message ="You can try searching for a country again.\n Or try again later."))
            }catch (e:HttpException){
                emit(Response.Error(message = "Check your internet connection.."))
            }
        }
    }
}