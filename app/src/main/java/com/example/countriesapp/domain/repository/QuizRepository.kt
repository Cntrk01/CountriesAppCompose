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
}