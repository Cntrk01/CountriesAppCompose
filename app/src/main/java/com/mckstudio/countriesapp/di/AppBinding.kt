package com.mckstudio.countriesapp.di

import com.mckstudio.countriesapp.data.repositoryimpl.CountryRepositoryImpl
import com.mckstudio.countriesapp.data.repositoryimpl.FavoriteCountryRepositoryImpl
import com.mckstudio.countriesapp.data.repositoryimpl.HomeRepositoryImpl
import com.mckstudio.countriesapp.data.repositoryimpl.QuizRepositoryImpl
import com.mckstudio.countriesapp.domain.repository.CountryRepository
import com.mckstudio.countriesapp.domain.repository.FavoriteCountryRepository
import com.mckstudio.countriesapp.domain.repository.HomeRepository
import com.mckstudio.countriesapp.domain.repository.QuizRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBinding {

    @Binds
    abstract fun bindCountryRepository(
        countryRepositoryImpl: CountryRepositoryImpl
    ): CountryRepository

    @Binds
    abstract fun bindFavoriteRepository(
        favoriteRepositoryImpl : FavoriteCountryRepositoryImpl
    ) : FavoriteCountryRepository

    @Binds
    abstract fun bindQuizRepository(
        quizRepositoryImpl: QuizRepositoryImpl
    ) : QuizRepository

    @Binds
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ) : HomeRepository
}