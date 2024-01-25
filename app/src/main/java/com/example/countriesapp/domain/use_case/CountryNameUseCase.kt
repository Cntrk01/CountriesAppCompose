package com.example.countriesapp.domain.use_case

import com.example.countriesapp.data.repositoryimpl.CountryRepositoryImpl
import javax.inject.Inject

class CountryNameUseCase @Inject constructor(private val countryRepositoryImpl: CountryRepositoryImpl) {
    suspend operator fun invoke (name:String) = countryRepositoryImpl.getCountryWithName(name = name)
}