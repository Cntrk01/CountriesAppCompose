package com.example.countriesapp.domain.use_case

import com.example.countriesapp.data.repositoryimpl.QuizRepositoryImpl
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.QuizItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizUseCase @Inject constructor(private val quizRepositoryImpl: QuizRepositoryImpl) {
    suspend operator fun invoke() : Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getEasyQuizFlagQuestion()

    suspend fun getEasyQuizCapitalQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getEasyQuizCapitalQuestion()

    suspend fun getEasyQuizEmblemsQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getEasyQuizEmblemsQuestion()

    suspend fun getMediumQuizFlagQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getMediumQuizFlagQuestion()

    suspend fun getMediumQuizCapitalQuestion(): Flow<Response<List<QuizItem>>>  = quizRepositoryImpl.getMediumQuizCapitalQuestion()

    suspend fun getMediumQuizEmblemsQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getMediumQuizEmblemsQuestion()

    suspend fun getHardQuizFlagQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getHardQuizFlagQuestion()

    suspend fun getHardQuizCapitalQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getHardQuizCapitalQuestion()

    suspend fun getHardQuizEmblemsQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getHardQuizEmblemsQuestion()

    suspend fun getExpertQuizFlagQuestion() : Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getExpertQuizFlagQuestion()

    suspend fun getExpertQuizCapitalQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getExpertQuizCapitalQuestion()

    suspend fun getExpertQuizEmblemsQuestion(): Flow<Response<List<QuizItem>>> =  quizRepositoryImpl.getExpertQuizEmblemsQuestion()
}