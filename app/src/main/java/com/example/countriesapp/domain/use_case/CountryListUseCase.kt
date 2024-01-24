package com.example.countriesapp.domain.use_case

import com.example.countriesapp.data.repositoryimpl.CountryRepositoryImpl
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.CountryItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryListUseCase @Inject constructor(private val countryRepositoryImpl: CountryRepositoryImpl) {
    suspend operator fun invoke(page:Int) : Flow<Response<List<CountryItem>>> = countryRepositoryImpl.getAllCountry(page = page)
}