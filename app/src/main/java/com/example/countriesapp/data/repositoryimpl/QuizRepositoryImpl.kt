package com.example.countriesapp.data.repositoryimpl

import com.example.countriesapp.data.remote.CountryApi
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.data.response.toQuizItem
import com.example.countriesapp.domain.model.QuizItem
import com.example.countriesapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(private val countryApi: CountryApi) : QuizRepository {

    override suspend fun getEasyQuizQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val asiaCountry = countryApi.getCountryWithRegion("Asia").map { it.toQuizItem() }.take(20)
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItem() }.take(20)
            emit(Response.Success(data = asiaCountry+europeCountry))
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }
    }

}