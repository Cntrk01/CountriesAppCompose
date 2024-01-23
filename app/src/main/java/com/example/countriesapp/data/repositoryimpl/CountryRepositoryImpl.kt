package com.example.countriesapp.data.repositoryimpl

import com.example.countriesapp.data.remote.CountryApi
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.data.response.toCountryDetailItem
import com.example.countriesapp.data.response.toCountryItem
import com.example.countriesapp.domain.model.CountryDetailItem
import com.example.countriesapp.domain.model.CountryItem
import com.example.countriesapp.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val countryApi: CountryApi) :
    CountryRepository {

    override suspend fun getAllCountry(page:Int): Flow<Response<List<CountryItem>>> {
        return flow {
            try {
                val startingIndex = page * 10
                val data = countryApi.getAllCountry().map {
                    it.toCountryItem()
                }.drop(startingIndex).take(10)
                Response.Success(data = data)
            } catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
            }
            catch (e: HttpException){
                emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
            }catch (e: IOException){
                emit(Response.Error("Couldn't reach server.Check your internet connection.."))
            }
        }
    }

    override suspend fun getCountryWithName(name: String): Flow<Response<List<CountryDetailItem>>> {
        return flow {
            try {
                emit(Response.Loading())
                val data = countryApi.getCountryWithName(name = name).map {
                    it.toCountryDetailItem()
                }
                emit(Response.Success(data = data))
            } catch (e: Exception) {
                emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
            }
            catch (e: HttpException){
                emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
            }catch (e: IOException){
                emit(Response.Error("Couldn't reach server.Check your internet connection.."))
            }
        }
    }
}