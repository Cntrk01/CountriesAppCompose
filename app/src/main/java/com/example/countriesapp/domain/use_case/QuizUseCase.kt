package com.example.countriesapp.domain.use_case

import com.example.countriesapp.data.repositoryimpl.QuizRepositoryImpl
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.QuizItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizUseCase @Inject constructor(private val quizRepositoryImpl: QuizRepositoryImpl) {
    suspend operator fun invoke() : Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getEasyQuizQuestion()
}