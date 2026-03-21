package com.mckstudio.countriesapp.domain.model

import androidx.room.TypeConverter
import com.mckstudio.countriesapp.data.model.CapitalInfo
import com.mckstudio.countriesapp.data.model.CoatOfArms
import com.mckstudio.countriesapp.data.model.Currency
import com.mckstudio.countriesapp.data.model.Flags
import com.mckstudio.countriesapp.data.model.Maps
import com.mckstudio.countriesapp.data.model.Name
import com.mckstudio.countriesapp.data.model.PostalCode
import com.mckstudio.countriesapp.data.model.Translations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Conventers {
    private val gson = Gson()

    @TypeConverter
    fun fromTranslationList(value: List<BaseTranslation>?): String {
        return gson.toJson(value ?: emptyList<BaseTranslation>())
    }

    @TypeConverter
    fun toTranslationList(value: String): List<BaseTranslation> {
        val listType = object : TypeToken<List<BaseTranslation>>() {}.type
        return gson.fromJson(value, listType) ?: emptyList()
    }

    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun stringListToString(value: List<String>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromStringMap(value: String?): Map<String, Currency>? {
        if (value == null) {
            return null
        }
        val mapType = object : TypeToken<Map<String, Currency>>() {}.type
        return gson.fromJson(value, mapType)
    }

    @TypeConverter
    fun stringMapToString(value: Map<String, Currency>?): String? {
        return gson.toJson(value)
    }


    @TypeConverter
    fun fromString(value: String?): List<Double>? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<List<Double>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(value: List<Double>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromCapitalInfo(value: String?): CapitalInfo? {
        if (value == null) {
            return null
        }
        return gson.fromJson(value, CapitalInfo::class.java)
    }

    @TypeConverter
    fun capitalInfoToString(value: CapitalInfo?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromFlags(value: String?): Flags? {
        if (value == null) {
            return null
        }
        return gson.fromJson(value, Flags::class.java)
    }

    @TypeConverter
    fun flagsToString(value: Flags?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromMaps(value: String?): Maps? {
        if (value == null) {
            return null
        }
        return gson.fromJson(value, Maps::class.java)
    }

    @TypeConverter
    fun mapsToString(value: Maps?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromName(value: String?): Name? {
        if (value == null) {
            return null
        }
        return gson.fromJson(value, Name::class.java)
    }

    @TypeConverter
    fun nameToString(value: Name?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromPostalCode(value: String?): PostalCode? {
        if (value == null) {
            return null
        }
        return gson.fromJson(value, PostalCode::class.java)
    }

    @TypeConverter
    fun postalCodeToString(value: PostalCode?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromTranslations(value: String?): Translations? {
        if (value == null) {
            return null
        }
        return gson.fromJson(value, Translations::class.java)
    }

    @TypeConverter
    fun translationsToString(value: Translations?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromCoatOfArms(value: String?): CoatOfArms? {
        if (value == null) {
            return null
        }
        return gson.fromJson(value, CoatOfArms::class.java)
    }

    @TypeConverter
    fun coatOfArmsToString(value: CoatOfArms?): String? {
        return gson.toJson(value)
    }
}