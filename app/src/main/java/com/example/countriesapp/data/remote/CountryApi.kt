package com.example.countriesapp.data.remote

import com.example.countriesapp.data.response.BaseList
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {
    @GET("all")
    suspend fun getAllCountry () : BaseList

    @GET("name/{name}")
    suspend fun getCountryWithName(
        @Path("name") name:String
    )
}