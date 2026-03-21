package com.mckstudio.countriesapp.domain.repository

import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.data.model.countrydetail.DetailResponse
import kotlinx.coroutines.flow.Flow

interface DetailRepository{
    suspend fun getCountryDetail(name: String): Flow<Response<DetailResponse>>
}
