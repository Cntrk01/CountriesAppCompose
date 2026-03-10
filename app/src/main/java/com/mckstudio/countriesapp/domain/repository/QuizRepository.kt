package com.mckstudio.countriesapp.domain.repository

import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.domain.model.QuizItem
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
     fun getEasyQuizFlagQuestion() : Flow<Response<List<QuizItem>>>

     fun getEasyQuizCapitalQuestion() : Flow<Response<List<QuizItem>>>

     fun getEasyQuizEmblemsQuestion() : Flow<Response<List<QuizItem>>>

     fun getMediumQuizFlagQuestion() : Flow<Response<List<QuizItem>>>

     fun getMediumQuizCapitalQuestion() : Flow<Response<List<QuizItem>>>

     fun getMediumQuizEmblemsQuestion() : Flow<Response<List<QuizItem>>>

     fun getHardQuizFlagQuestion() : Flow<Response<List<QuizItem>>>

     fun getHardQuizCapitalQuestion() : Flow<Response<List<QuizItem>>>

     fun getHardQuizEmblemsQuestion() : Flow<Response<List<QuizItem>>>

     fun getExpertQuizFlagQuestion() : Flow<Response<List<QuizItem>>>

     fun getExpertQuizCapitalQuestion() : Flow<Response<List<QuizItem>>>

     fun getExpertQuizEmblemsQuestion() : Flow<Response<List<QuizItem>>>

     fun getEuropeCountryQuizQuestion() : Flow<Response<List<QuizItem>>>

     fun getAmericaCountryQuizQuestion() : Flow<Response<List<QuizItem>>>

     fun getAfricaCountryQuizQuestion() : Flow<Response<List<QuizItem>>>

     fun getAsiaCountryQuizQuestion() : Flow<Response<List<QuizItem>>>

     fun getOceaniaCountryQuizQuestion() : Flow<Response<List<QuizItem>>>

}