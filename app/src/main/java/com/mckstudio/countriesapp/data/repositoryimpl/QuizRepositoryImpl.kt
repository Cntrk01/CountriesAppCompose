package com.mckstudio.countriesapp.data.repositoryimpl

import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.data.remote.CountryApi
import com.mckstudio.countriesapp.data.response.toQuizItem
import com.mckstudio.countriesapp.data.response.toQuizItemCapital
import com.mckstudio.countriesapp.data.response.toQuizItemEmblems
import com.mckstudio.countriesapp.domain.repository.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val countryApi: CountryApi
) : QuizRepository {

    // ---------------- EASY ----------------

    override suspend fun getEasyQuizFlagQuestion() =
        Response.safeOperation {
            countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItem() }
        }

    override suspend fun getEasyQuizCapitalQuestion() =
        Response.safeOperation {
            countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItemCapital() }
                .subList(0, 10)
        }

    override suspend fun getEasyQuizEmblemsQuestion() =
        Response.safeOperation {
            countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItemEmblems() }
                .take(10)
        }

    // ---------------- MEDIUM ----------------

    override suspend fun getMediumQuizFlagQuestion() =
        Response.safeOperation {
            val europe = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItem() }

            val asia = countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItem() }

            europe.subList(10, 20) + asia.subList(10, 20)
        }

    override suspend fun getMediumQuizCapitalQuestion() =
        Response.safeOperation {
            val europe = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItemCapital() }

            val asia = countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItemCapital() }

            europe.subList(10, 20) + asia.subList(10, 20)
        }

    override suspend fun getMediumQuizEmblemsQuestion() =
        Response.safeOperation {
            val europe = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItemEmblems() }

            val asia = countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItemEmblems() }

            europe.subList(10, 20) + asia.subList(10, 20)
        }

    // ---------------- HARD ----------------

    override suspend fun getHardQuizFlagQuestion() =
        Response.safeOperation {
            val europe = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItem() }

            val asia = countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItem() }

            val america = countryApi.getCountryWithRegion("America")
                .map { it.toQuizItem() }

            europe.subList(20, 30) +
                    asia.subList(20, 30) +
                    america.subList(20, 30)
        }

    override suspend fun getHardQuizCapitalQuestion() =
        Response.safeOperation {
            val europe = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItemCapital() }

            val asia = countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItemCapital() }

            val america = countryApi.getCountryWithRegion("America")
                .map { it.toQuizItemCapital() }

            europe.subList(20, 30) +
                    asia.subList(20, 30) +
                    america.subList(20, 30)
        }

    override suspend fun getHardQuizEmblemsQuestion() =
        Response.safeOperation {
            val europe = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItemEmblems() }

            val asia = countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItemEmblems() }

            val america = countryApi.getCountryWithRegion("America")
                .map { it.toQuizItemEmblems() }

            europe.subList(20, 30) +
                    asia.subList(20, 30) +
                    america.subList(20, 30)
        }

    // ---------------- EXPERT ----------------

    override suspend fun getExpertQuizFlagQuestion() =
        Response.safeOperation {
            val europe = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItem() }

            val asia = countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItem() }

            val america = countryApi.getCountryWithRegion("America")
                .map { it.toQuizItem() }

            val africa = countryApi.getCountryWithRegion("Africa")
                .map { it.toQuizItem() }

            europe.subList(30, 40) +
                    asia.subList(30, 40) +
                    america.subList(30, 40) +
                    africa.subList(0, 10)
        }

    override suspend fun getExpertQuizCapitalQuestion() =
        Response.safeOperation {
            val europe = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItemCapital() }

            val asia = countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItemCapital() }

            val america = countryApi.getCountryWithRegion("America")
                .map { it.toQuizItemCapital() }

            val africa = countryApi.getCountryWithRegion("Africa")
                .map { it.toQuizItemCapital() }

            europe.subList(30, 40) +
                    asia.subList(30, 40) +
                    america.subList(30, 40) +
                    africa.subList(0, 10)
        }

    override suspend fun getExpertQuizEmblemsQuestion() =
        Response.safeOperation {
            val europe = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItemEmblems() }

            val asia = countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItemEmblems() }

            val america = countryApi.getCountryWithRegion("America")
                .map { it.toQuizItemEmblems() }

            val africa = countryApi.getCountryWithRegion("Africa")
                .map { it.toQuizItemEmblems() }

            europe.subList(30, 40) +
                    asia.subList(30, 40) +
                    america.subList(30, 40) +
                    africa.subList(0, 10)
        }

    // ---------------- REGION BASED ----------------

    override suspend fun getEuropeCountryQuizQuestion() =
        Response.safeOperation {
            countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItem() }
        }

    override suspend fun getAmericaCountryQuizQuestion() =
        Response.safeOperation {
            countryApi.getCountryWithRegion("America")
                .map { it.toQuizItem() }
        }

    override suspend fun getAfricaCountryQuizQuestion() =
        Response.safeOperation {
            countryApi.getCountryWithRegion("Africa")
                .map { it.toQuizItem() }
        }

    override suspend fun getAsiaCountryQuizQuestion() =
        Response.safeOperation {
            countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItem() }
        }

    override suspend fun getOceaniaCountryQuizQuestion() =
        Response.safeOperation {
            countryApi.getCountryWithRegion("Oceania")
                .map { it.toQuizItem() }
        }
}
