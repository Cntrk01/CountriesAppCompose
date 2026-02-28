package com.mckstudio.countriesapp.data.repositoryimpl

import com.mckstudio.countriesapp.data.remote.CountryApi
import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.data.response.toCountryItem
import com.mckstudio.countriesapp.domain.model.CountryItem
import com.mckstudio.countriesapp.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException

class SearchCountryRepositoryImpl(private val countryApi: CountryApi) : SearchRepository {

    override suspend fun searchRepository(countryName: String): Flow<Response<List<CountryItem>>> =
        Response.safeOperation {
            countryApi.getCountryWithName(name = countryName).map { it.toCountryItem() }
        }
}