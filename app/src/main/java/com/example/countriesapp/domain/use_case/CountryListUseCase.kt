package com.example.countriesapp.domain.use_case

import com.example.countriesapp.data.repositoryimpl.CountryRepositoryImpl
import javax.inject.Inject

class CountryListUseCase @Inject constructor(private val countryRepositoryImpl: CountryRepositoryImpl) {
    suspend fun getAllCountry(page:Int)=countryRepositoryImpl.getAllCountry(page = page)
}