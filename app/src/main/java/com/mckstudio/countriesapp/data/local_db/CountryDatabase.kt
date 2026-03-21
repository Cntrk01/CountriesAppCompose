package com.mckstudio.countriesapp.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mckstudio.countriesapp.domain.model.Conventers
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.domain.newmodel.CountryDetailEntity

@Database(entities = [
        CountryDetailItem::class,
        CountryDetailEntity::class,
    ],
    version =6,
    exportSchema = false,
)
@TypeConverters(Conventers::class)
abstract class CountryDatabase : RoomDatabase(){
    abstract fun countryDao() : CountryDao
}