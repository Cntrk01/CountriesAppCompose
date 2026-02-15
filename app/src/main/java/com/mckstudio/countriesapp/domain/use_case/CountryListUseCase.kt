package com.mckstudio.countriesapp.domain.use_case

import com.mckstudio.countriesapp.data.repositoryimpl.CountryRepositoryImpl
import com.mckstudio.countriesapp.data.response.Response
import com.mckstudio.countriesapp.domain.model.CountryItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryListUseCase @Inject constructor(private val countryRepositoryImpl: CountryRepositoryImpl) {
    suspend operator fun invoke() : Flow<Response<List<CountryItem>>> = countryRepositoryImpl.getAllCountry()
}