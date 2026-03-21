package com.mckstudio.countriesapp.di

import com.mckstudio.countriesapp.data.repositoryimpl.CountryRepositoryImpl
import com.mckstudio.countriesapp.data.repositoryimpl.DetailRepositoryImpl
import com.mckstudio.countriesapp.data.repositoryimpl.FavoriteRepositoryImpl
import com.mckstudio.countriesapp.data.repositoryimpl.HomeRepositoryImpl
import com.mckstudio.countriesapp.data.repositoryimpl.QuizRepositoryImpl
import com.mckstudio.countriesapp.domain.repository.CountryRepository
import com.mckstudio.countriesapp.domain.repository.DetailRepository
import com.mckstudio.countriesapp.domain.repository.FavoriteRepository
import com.mckstudio.countriesapp.domain.repository.HomeRepository
import com.mckstudio.countriesapp.domain.repository.QuizRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBinding {

    @Binds
    abstract fun bindCountryRepository(
        countryRepositoryImpl: CountryRepositoryImpl
    ): CountryRepository

    @Binds
    abstract fun bindFavoriteRepository(
        favoriteRepositoryImpl : FavoriteRepositoryImpl
    ) : FavoriteRepository

    @Binds
    abstract fun bindQuizRepository(
        quizRepositoryImpl: QuizRepositoryImpl
    ) : QuizRepository

    @Binds
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ) : HomeRepository

    @Binds
    abstract fun bindDetailRepository(
        detailRepositoryImpl: DetailRepositoryImpl
    ) : DetailRepository

}