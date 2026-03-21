package com.mckstudio.countriesapp.data.repositoryimpl

import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.data.remote.CountryApi
import com.mckstudio.countriesapp.data.model.countrydetail.DetailResponse
import com.mckstudio.countriesapp.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val api: CountryApi
) : DetailRepository {
    override suspend fun getCountryDetail(name: String): Flow<Response<DetailResponse>> {
        return Response.safeOperation {
            api.getCountryDetailWithName(name = name)
        }
    }
}
