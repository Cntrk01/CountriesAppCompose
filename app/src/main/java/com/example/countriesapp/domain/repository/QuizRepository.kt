package com.example.countriesapp.domain.repository

import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.QuizItem
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    suspend fun getEasyQuizQuestion() : Flow<Response<List<QuizItem>>>
}