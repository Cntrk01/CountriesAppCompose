package com.mckstudio.countriesapp.di

import android.content.Context
import androidx.room.Room
import com.mckstudio.countriesapp.common.Constants.BASE_URL
import com.mckstudio.countriesapp.data.local_db.CountryDao
import com.mckstudio.countriesapp.data.local_db.CountryDatabase
import com.mckstudio.countriesapp.data.remote.CountryApi
import com.mckstudio.countriesapp.data.repositoryimpl.FavoriteCountryRepositoryImpl
import com.mckstudio.countriesapp.data.repositoryimpl.SearchCountryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit() : CountryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalDatabase (@ApplicationContext context: Context) : CountryDatabase {
        return Room.databaseBuilder(context = context, klass = CountryDatabase::class.java, name = "country_db1")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryDao(countryDb: CountryDatabase) : CountryDao {
        return countryDb.countryDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteCountryRepositoryImpl(countryDao: CountryDao) : FavoriteCountryRepositoryImpl {
        return FavoriteCountryRepositoryImpl(countryDao = countryDao)
    }

    @Provides
    @Singleton
    fun provideSearchCountryImpl (countryApi: CountryApi) : SearchCountryRepositoryImpl {
        return SearchCountryRepositoryImpl(countryApi = countryApi)
    }
}