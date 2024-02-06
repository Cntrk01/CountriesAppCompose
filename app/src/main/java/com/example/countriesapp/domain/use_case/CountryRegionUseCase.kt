package com.example.countriesapp.domain.use_case

import com.example.countriesapp.data.repositoryimpl.CountryRepositoryImpl
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.CountryItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryRegionUseCase @Inject constructor(private val countryRepositoryImpl: CountryRepositoryImpl) {
    suspend operator fun invoke(regionName: String): Flow<Response<List<CountryItem>>> =
        countryRepositoryImpl.getCountryWithRegion(regionName = regionName)
}