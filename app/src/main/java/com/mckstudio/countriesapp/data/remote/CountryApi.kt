package com.mckstudio.countriesapp.data.remote

import com.mckstudio.countriesapp.data.model.BaseList
import com.mckstudio.countriesapp.data.model.countrydetail.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {
    @GET("all")
    suspend fun getAllCountry () : BaseList

    @GET("name/{name}")
    suspend fun getCountryWithName(
        @Path("name") name:String
    ) : BaseList

    @GET("region/{region}")
    suspend fun getCountryWithRegion(
        @Path("region") region:String
    ) : BaseList

    @GET("name/{name}")
    suspend fun getCountryDetailWithName(
        @Path("name") name:String
    ) : DetailResponse
}