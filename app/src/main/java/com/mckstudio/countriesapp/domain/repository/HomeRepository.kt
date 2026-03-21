package com.mckstudio.countriesapp.domain.repository

import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.domain.model.CountryItem
import com.mckstudio.countriesapp.domain.model.RecommendedItem
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun searchCountry(query: String): Flow<Response<List<CountryItem>>>
    fun getRandomCountry() : Flow<Response<List<RecommendedItem>>>
}