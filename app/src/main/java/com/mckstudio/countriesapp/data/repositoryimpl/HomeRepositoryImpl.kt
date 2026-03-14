package com.mckstudio.countriesapp.data.repositoryimpl

import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.data.remote.CountryApi
import com.mckstudio.countriesapp.data.response.toCountryItem
import com.mckstudio.countriesapp.domain.model.CountryItem
import com.mckstudio.countriesapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val countryApi: CountryApi
) : HomeRepository {
    override fun searchCountry(query: String): Flow<Response<List<CountryItem>>> {
        return Response.safeOperation {
            countryApi.getCountryWithName(name = query).map { it.toCountryItem() }
        }
    }
}