package com.mckstudio.countriesapp.data.repositoryimpl

import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.data.remote.CountryApi
import com.mckstudio.countriesapp.data.model.toCountryItem
import com.mckstudio.countriesapp.data.model.toRecommendedItem
import com.mckstudio.countriesapp.domain.model.CountryItem
import com.mckstudio.countriesapp.domain.model.RecommendedItem
import com.mckstudio.countriesapp.domain.repository.HomeRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val countryApi: CountryApi
) : HomeRepository {
    override fun searchCountry(query: String): Flow<Response<List<CountryItem>>> {
        return Response.safeOperation {
            countryApi.getCountryWithName(name = query).map { it.toCountryItem() }
        }
    }

    override fun getRandomCountry(): Flow<Response<List<RecommendedItem>>> {
        // Böyle yaparsak her istek için tek tek istek atıcak ilkinde birisi ikincisinde birisi vs olucak ama
        // aşağıdaki gibi yaparsak paralelde kaç tane eleman varsa o kadar istek atıcak böylelikle loading süresi kısalıcak.
        // return Response.safeOperation {
        //    var randomCountries = listOf<RecommendedItem>()
        //    listOf("Switzerland","Maldives","France","Germany","Italy","Spain").forEach { query ->
        //        randomCountries = countryApi.getCountryWithName(name = query).map { it.toRecommendedItem() }
        //    }
        //    return@safeOperation randomCountries
        //}

        return Response.safeOperation {
            val countryNames = listOf("Switzerland", "Maldives", "France", "Germany", "Italy", "Spain")

            // CoroutineScope kullanarak istekleri paralel başlatıyoruz
            coroutineScope {
                countryNames.map { query ->
                    async {
                        countryApi.getCountryWithName(name = query)
                            .firstOrNull()
                            ?.toRecommendedItem()
                    }
                }.awaitAll().filterNotNull() // Tümünü bekle ve null olanları temizle
            }
        }
    }
}