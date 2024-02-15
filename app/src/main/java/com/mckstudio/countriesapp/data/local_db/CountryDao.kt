package com.mckstudio.countriesapp.data.local_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mckstudio.countriesapp.data.response.Name
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(countryDetailItem: CountryDetailItem)

    @Delete
    suspend fun deleteCountry(countryDetailItem: CountryDetailItem)

    @Query("SELECT*FROM country_item")
    fun getAllCountry () : Flow<List<CountryDetailItem>>

    @Query("SELECT COUNT(*) FROM country_item WHERE country_item.name=:name")
    suspend fun checkExistCountry(name: Name) : Int
}