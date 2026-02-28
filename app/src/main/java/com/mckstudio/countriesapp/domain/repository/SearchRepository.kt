package com.mckstudio.countriesapp.domain.repository

import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.domain.model.CountryItem
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchRepository(countryName:String) : Flow<Response<List<CountryItem>>>
}