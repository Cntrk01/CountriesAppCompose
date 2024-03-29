package com.mckstudio.countriesapp.domain.use_case

import com.mckstudio.countriesapp.data.repositoryimpl.QuizRepositoryImpl
import com.mckstudio.countriesapp.data.response.Response
import com.mckstudio.countriesapp.domain.model.QuizItem
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

    suspend fun getEuropeCountryQuizQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getEuropeCountryQuizQuestion()

    suspend fun getAmericaCountryQuizQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getAmericaCountryQuizQuestion()

    suspend fun getAfricaCountryQuizQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getAfricaCountryQuizQuestion()

    suspend fun getAsiaCountryQuizQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getAsiaCountryQuizQuestion()

    suspend fun getOceaniaCountryQuizQuestion(): Flow<Response<List<QuizItem>>> = quizRepositoryImpl.getOceaniaCountryQuizQuestion()
}