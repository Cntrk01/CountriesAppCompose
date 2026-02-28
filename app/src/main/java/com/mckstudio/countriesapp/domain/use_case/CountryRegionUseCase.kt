package com.mckstudio.countriesapp.domain.use_case

import com.mckstudio.countriesapp.data.repositoryimpl.CountryRepositoryImpl
import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.domain.model.CountryItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryRegionUseCase @Inject constructor(private val countryRepositoryImpl: CountryRepositoryImpl) {
    suspend operator fun invoke(regionName: String): Flow<Response<List<CountryItem>>> =
        countryRepositoryImpl.getCountryWithRegion(regionName = regionName)
}