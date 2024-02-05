package com.example.countriesapp.domain.model

import androidx.room.TypeConverter
import com.example.countriesapp.data.response.CapitalInfo
import com.example.countriesapp.data.response.CoatOfArms
import com.example.countriesapp.data.response.Currency
import com.example.countriesapp.data.response.Flags
import com.example.countriesapp.data.response.Maps
import com.example.countriesapp.data.response.Name
import com.example.countriesapp.data.response.PostalCode
import com.example.countriesapp.data.response.Translations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Conventers {
    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun stringListToString(value: List<String>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringMap(value: String?): Map<String, Currency>? {
        if (value == null) {
            return null
        }
        val mapType = object : TypeToken<Map<String, Currency>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun stringMapToString(value: Map<String, Currency>?): String? {
        return Gson().toJson(value)
    }


    @TypeConverter
    fun fromString(value: String?): List<Double>? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<List<Double>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(value: List<Double>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromCapitalInfo(value: String?): CapitalInfo? {
        if (value == null) {
            return null
        }
        return Gson().fromJson(value, CapitalInfo::class.java)
    }

    @TypeConverter
    fun capitalInfoToString(value: CapitalInfo?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromFlags(value: String?): Flags? {
        if (value == null) {
            return null
        }
        return Gson().fromJson(value, Flags::class.java)
    }

    @TypeConverter
    fun flagsToString(value: Flags?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromMaps(value: String?): Maps? {
        if (value == null) {
            return null
        }
        return Gson().fromJson(value, Maps::class.java)
    }

    @TypeConverter
    fun mapsToString(value: Maps?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromName(value: String?): Name? {
        if (value == null) {
            return null
        }
        return Gson().fromJson(value, Name::class.java)
    }

    @TypeConverter
    fun nameToString(value: Name?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromPostalCode(value: String?): PostalCode? {
        if (value == null) {
            return null
        }
        return Gson().fromJson(value, PostalCode::class.java)
    }

    @TypeConverter
    fun postalCodeToString(value: PostalCode?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromTranslations(value: String?): Translations? {
        if (value == null) {
            return null
        }
        return Gson().fromJson(value, Translations::class.java)
    }

    @TypeConverter
    fun translationsToString(value: Translations?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromCoatOfArms(value: String?): CoatOfArms? {
        if (value == null) {
            return null
        }
        return Gson().fromJson(value, CoatOfArms::class.java)
    }

    @TypeConverter
    fun coatOfArmsToString(value: CoatOfArms?): String? {
        return Gson().toJson(value)
    }
}