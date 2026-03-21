package com.mckstudio.countriesapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mckstudio.countriesapp.common.Constants.BASE_URL
import com.mckstudio.countriesapp.data.local_db.CountryDao
import com.mckstudio.countriesapp.data.local_db.CountryDatabase
import com.mckstudio.countriesapp.data.remote.CountryApi
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
    fun provideRetrofit(): CountryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): CountryDatabase {
        val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Yeni tabloyu manuel oluşturmalıyız çünkü Room otomatik yapmaz.Buna bağlı olarak önceden
                //id int değerinde primary keydi artık name olarak da güncelledik.
                db.execSQL("""
            CREATE TABLE IF NOT EXISTS `favorite_countries` (
                `name` TEXT NOT NULL, 
                `commonName` TEXT NOT NULL, 
                `officialName` TEXT NOT NULL, 
                `capital` TEXT NOT NULL, 
                `currency` TEXT NOT NULL, 
                `flagUrl` TEXT NOT NULL, 
                `population` TEXT NOT NULL, 
                `languages` TEXT NOT NULL, 
                `region` TEXT NOT NULL, 
                `subregion` TEXT NOT NULL, 
                `coatOfArmsUrl` TEXT NOT NULL, 
                `googleMaps` TEXT NOT NULL, 
                `translations` TEXT NOT NULL, 
                `status` TEXT NOT NULL, 
                 PRIMARY KEY(`name`) 
            )
        """.trimIndent())
            }
        }

        return Room
            .databaseBuilder(
                context = context,
                klass = CountryDatabase::class.java,
                name = "country_db1",
            )
            .allowMainThreadQueries()
            .addMigrations(MIGRATION_5_6)
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryDao(countryDb: CountryDatabase): CountryDao {
        return countryDb.countryDao()
    }
}