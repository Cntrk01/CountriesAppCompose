package com.mckstudio.countriesapp.data.repositoryimpl

import com.mckstudio.countriesapp.common.Constants
import com.mckstudio.countriesapp.data.remote.CountryApi
import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.data.response.toCountryDetailItem
import com.mckstudio.countriesapp.data.response.toCountryItem
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.domain.model.CountryItem
import com.mckstudio.countriesapp.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepositoryImpl @Inject constructor(private val countryApi: CountryApi) :
    CountryRepository {
    private var regionList : List<CountryItem> = listOf()

    override suspend fun getAllCountry(): Flow<Response<List<CountryItem>>> {

        if (regionList.isNotEmpty()) {
            return flowOf(Response.Success(regionList))
        }

        return Response.safeOperation {
            val allCountries = mutableListOf<CountryItem>()

            Constants.regions.forEach { region ->
                val response = countryApi.getCountryWithRegion(region)
                allCountries += response.map { it.toCountryItem() }
            }

            regionList = allCountries
            allCountries
        }
    }

    override suspend fun getCountryWithName(name: String): Flow<Response<List<CountryDetailItem>>> =
        Response.safeOperation {
            countryApi.getCountryWithName(name = name).map {
                it.toCountryDetailItem()
            }
        }

    override suspend fun getCountryWithRegion(
        regionName: String
    ): Flow<Response<List<CountryItem>>> =
        Response.safeOperation {
            countryApi.getCountryWithRegion(region = regionName).map {
                it.toCountryItem()
            }
        }
}