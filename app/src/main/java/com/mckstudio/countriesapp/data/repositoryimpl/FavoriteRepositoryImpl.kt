package com.mckstudio.countriesapp.data.repositoryimpl

import com.mckstudio.countriesapp.data.local_db.CountryDao
import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.data.mapper.toNewEntity
import com.mckstudio.countriesapp.domain.newmodel.CountryDetailEntity
import com.mckstudio.countriesapp.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val countryDao: CountryDao,
) : FavoriteRepository {

    override fun getAllCountry(): Flow<Response<List<CountryDetailEntity>>> =
        Response.safeOperation {
            countryDao.getAllFavorites()
        }

    override suspend fun deleteCountry(countryName: String): Flow<Response<Unit>> =
        Response.safeOperation {
            countryDao.deleteFavoriteByName(countryName = countryName)
        }

    override suspend fun insertCountry(countryDetailItem: CountryDetailEntity): Flow<Response<Unit>> =
        Response.safeOperation {
            countryDao.insertFavorite(country = countryDetailItem)
        }

    override suspend fun getFavoriteByName(name: String): Flow<Response<CountryDetailEntity?>> =
        Response.safeOperation {
            countryDao.getFavoriteByName(name = name)
        }

    override suspend fun performMigrationIfNeeded() = withContext(Dispatchers.IO + NonCancellable) {
        val pageSize = 100
        var hasMoreData = true

        while (hasMoreData && isActive) {
            //offseti 0 verdim.Çünkü 100 veri silindi.100 veri geri kayacak.Ve tekrar 0dan başlayarak yenilerini sileceğiz.
            val chunk = countryDao.getOldCountriesPaged(limit = pageSize, offset = 0)
            if (chunk.isNotEmpty()) {
                countryDao.moveChunkToNewTable(chunk)
                if (chunk.size < pageSize) hasMoreData = false
            } else {
                hasMoreData = false
            }
        }
    }
}