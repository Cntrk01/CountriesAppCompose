package com.mckstudio.countriesapp.domain.repository

import com.mckstudio.countriesapp.data.response.Response
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.domain.model.CountryItem
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    suspend fun getAllCountry(page:Int) : Flow<Response<List<CountryItem>>>

    suspend fun getCountryWithName (name:String) : Flow<Response<List<CountryDetailItem>>>

    suspend fun getCountryWithRegion(regionName:String) : Flow<Response<List<CountryItem>>>

}