package com.mckstudio.countriesapp.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mckstudio.countriesapp.domain.model.Conventers
import com.mckstudio.countriesapp.domain.model.CountryDetailItem

@Database(entities = [CountryDetailItem::class], version =5, exportSchema = false)
@TypeConverters(Conventers::class)
abstract class CountryDatabase : RoomDatabase(){
    abstract fun countryDao() : CountryDao
}