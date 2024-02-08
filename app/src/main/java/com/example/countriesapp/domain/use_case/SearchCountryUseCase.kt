package com.example.countriesapp.domain.use_case

import com.example.countriesapp.data.repositoryimpl.SearchCountryRepositoryImpl
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.CountryItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCountryUseCase @Inject constructor(private val searchCountryRepositoryImpl: SearchCountryRepositoryImpl) {
    suspend fun searchRepository(countryName: String): Flow<Response<List<CountryItem>>> = searchCountryRepositoryImpl.searchRepository(countryName = countryName)
}