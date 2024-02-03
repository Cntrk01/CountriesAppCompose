package com.example.countriesapp.domain.repository

import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.QuizItem
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    suspend fun getEasyQuizFlagQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getEasyQuizCapitalQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getEasyQuizEmblemsQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getMediumQuizFlagQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getMediumQuizCapitalQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getMediumQuizEmblemsQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getHardQuizFlagQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getHardQuizCapitalQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getHardQuizEmblemsQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getExpertQuizFlagQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getExpertQuizCapitalQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getExpertQuizEmblemsQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getEuropeCountryQuizQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getAmericaCountryQuizQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getAfricaCountryQuizQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getAsiaCountryQuizQuestion() : Flow<Response<List<QuizItem>>>

    suspend fun getOceaniaCountryQuizQuestion() : Flow<Response<List<QuizItem>>>

}