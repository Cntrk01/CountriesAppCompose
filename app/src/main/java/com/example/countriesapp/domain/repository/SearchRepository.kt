package com.example.countriesapp.domain.repository

import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.CountryItem
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchRepository(countryName:String) : Flow<Response<List<CountryItem>>>
}