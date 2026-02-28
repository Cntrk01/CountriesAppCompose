package com.mckstudio.countriesapp.di

import com.mckstudio.countriesapp.data.repositoryimpl.CountryRepositoryImpl
import com.mckstudio.countriesapp.domain.repository.CountryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBinding {

    @Binds
    @Singleton
    abstract fun bindCountryRepository(
        countryRepositoryImpl: CountryRepositoryImpl
    ): CountryRepository

}