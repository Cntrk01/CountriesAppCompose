package com.mckstudio.countriesapp.di

import com.mckstudio.countriesapp.data.repositoryimpl.CountryRepositoryImpl
import com.mckstudio.countriesapp.data.repositoryimpl.FavoriteCountryRepositoryImpl
import com.mckstudio.countriesapp.data.repositoryimpl.QuizRepositoryImpl
import com.mckstudio.countriesapp.data.repositoryimpl.SearchCountryRepositoryImpl
import com.mckstudio.countriesapp.domain.repository.CountryRepository
import com.mckstudio.countriesapp.domain.repository.FavoriteCountryRepository
import com.mckstudio.countriesapp.domain.repository.QuizRepository
import com.mckstudio.countriesapp.domain.repository.SearchRepository
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

    @Binds
    @Singleton
    abstract fun bindSearchRepository(
        searchCountryRepositoryImpl: SearchCountryRepositoryImpl
    ) : SearchRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(
        favoriteRepositoryImpl : FavoriteCountryRepositoryImpl
    ) : FavoriteCountryRepository

    @Binds
    @Singleton
    abstract fun bindQuizRepository(
        quizRepositoryImpl: QuizRepositoryImpl
    ) : QuizRepository
}