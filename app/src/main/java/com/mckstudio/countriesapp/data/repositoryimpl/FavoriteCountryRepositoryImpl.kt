package com.mckstudio.countriesapp.data.repositoryimpl

import com.mckstudio.countriesapp.data.local_db.CountryDao
import com.mckstudio.countriesapp.data.response.Name
import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.domain.repository.FavoriteCountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException

class FavoriteCountryRepositoryImpl(private val countryDao: CountryDao) :
    FavoriteCountryRepository {

    override fun getAllCountry(): Flow<Response<List<CountryDetailItem>>> =
        Response.safeOperation {
            countryDao.getAllCountry()
        }

    override suspend fun deleteCountry(countryDetailItem: CountryDetailItem): Flow<Response<Unit>> =
        Response.safeOperation {
            countryDao.deleteCountry(countryDetailItem = countryDetailItem)
        }

    override suspend fun insertCountry(countryDetailItem: CountryDetailItem): Flow<Response<Unit>> =
        Response.safeOperation {
            countryDao.insertCountry(countryDetailItem = countryDetailItem)
        }

    override suspend fun checkExistCountry(name: Name): Flow<Response<Int>> =
        Response.safeOperation {
            countryDao.checkExistCountry(name = name)
        }
}