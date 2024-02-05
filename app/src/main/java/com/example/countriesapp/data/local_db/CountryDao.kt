package com.example.countriesapp.data.local_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.countriesapp.data.response.Name
import com.example.countriesapp.domain.model.CountryRoomItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(countryDetailItem: CountryRoomItem)

    @Delete
    suspend fun deleteCountry(countryDetailItem: CountryRoomItem)

    @Query("SELECT*FROM country_item")
    fun getAllCountry () : Flow<List<CountryRoomItem>>

    @Query("SELECT COUNT(*) FROM country_item WHERE country_item.name=:name")
    suspend fun checkExistCountry(name: Name) : Int
}