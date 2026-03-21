package com.mckstudio.countriesapp.data.local_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mckstudio.countriesapp.data.mapper.toNewEntity
import com.mckstudio.countriesapp.data.model.Name
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.domain.newmodel.CountryDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(country: CountryDetailEntity)

    @Query("DELETE FROM favorite_countries WHERE name = :countryName")
    suspend fun deleteFavoriteByName(countryName: String)

    @Query("SELECT * FROM favorite_countries")
    suspend fun getAllFavorites(): List<CountryDetailEntity>

    @Query("SELECT * FROM favorite_countries WHERE commonName = :name LIMIT 1")
    suspend fun getFavoriteByName(name: String): CountryDetailEntity?


    // Not: Tüm tabloyu temizlemek yerine sadece taşınan ID'leri silmek
    // chunking yaparken daha güvenlidir.
    @Transaction
    suspend fun moveChunkToNewTable(chunk: List<CountryDetailItem>) {
        val newList = chunk.map { it.toNewEntity() }
        insertFavoriteList(newList)

        chunk
            .map { it.id }
            .also {
            deleteOldItemsById(it)
        }
    }

    @Query("SELECT * FROM country_item LIMIT :limit OFFSET :offset")
    suspend fun getOldCountriesPaged(limit: Int, offset: Int): List<CountryDetailItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteList(list: List<CountryDetailEntity>)

    @Query("DELETE FROM country_item WHERE id IN (:ids)")
    suspend fun deleteOldItemsById(ids: List<Int>)
}