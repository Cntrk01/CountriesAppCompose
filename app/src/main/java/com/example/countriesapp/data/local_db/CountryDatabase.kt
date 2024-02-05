package com.example.countriesapp.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.countriesapp.domain.model.Conventers
import com.example.countriesapp.domain.model.CountryRoomItem

@Database(entities = [CountryRoomItem::class], version = 1, exportSchema = false)
@TypeConverters(Conventers::class)
abstract class CountryDatabase : RoomDatabase(){
    abstract fun countryDao() : CountryDao
}