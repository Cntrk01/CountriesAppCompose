package com.mckstudio.countriesapp.data.repositoryimpl

import com.mckstudio.countriesapp.data.remote.CountryApi
import com.mckstudio.countriesapp.data.response.Response
import com.mckstudio.countriesapp.data.response.toQuizItem
import com.mckstudio.countriesapp.data.response.toQuizItemCapital
import com.mckstudio.countriesapp.data.response.toQuizItemEmblems
import com.mckstudio.countriesapp.domain.model.QuizItem
import com.mckstudio.countriesapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(private val countryApi: CountryApi) : QuizRepository {

    override suspend fun getEasyQuizFlagQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItem() }
            emit(Response.Success(data = europeCountry.subList(0,10)))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getEasyQuizCapitalQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItemCapital() }
            emit(Response.Success(data = europeCountry.subList(0,10)))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getEasyQuizEmblemsQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItemEmblems() }
                .take(10)
                .mapNotNull { quizItem ->
                    if (quizItem.flag.isNullOrEmpty()) {
                        countryApi.getCountryWithRegion("Europe").take(1).map { it.toQuizItemEmblems() }.let { newFlag ->
                            newFlag.map {
                                quizItem.copy(flag = it.flag)
                            }
                        }
                    } else {
                        listOf(quizItem)
                    }
                }.flatten()

            emit(Response.Success(data = europeCountry.subList(0,10)))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getMediumQuizFlagQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItem() }
            val asiaCountry=countryApi.getCountryWithRegion("Asia").map { it.toQuizItem() }
            emit(Response.Success(data = europeCountry.subList(10,20)+asiaCountry.subList(10,20)))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getMediumQuizCapitalQuestion(): Flow<Response<List<QuizItem>>>  = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItemCapital() }
            val asiaCountry=countryApi.getCountryWithRegion("Asia").map { it.toQuizItemCapital() }
            emit(Response.Success(data = europeCountry.subList(10,20)+asiaCountry.subList(10,20)))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getMediumQuizEmblemsQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItemEmblems() }
                .take(20)
                .mapNotNull { quizItem ->
                    if (quizItem.flag.isNullOrEmpty()) {
                        countryApi.getCountryWithRegion("Europe").take(1).map { it.toQuizItemEmblems() }.let { newFlag ->
                            newFlag.map {
                                quizItem.copy(flag = it.flag)
                            }
                        }
                    } else {
                        listOf(quizItem)
                    }
                }.flatten()

            val asiaCountry = countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItemEmblems() }
                .take(20)
                .map { quizItem ->
                    if (quizItem.flag.isNullOrEmpty()) {
                        countryApi.getCountryWithRegion("Asia").take(1).map { it.toQuizItemEmblems() }.let { newFlag ->
                            newFlag.map {
                                quizItem.copy(flag = it.flag)
                            }
                        }
                    } else {
                        listOf(quizItem)
                    }
                }.flatten()
            emit(Response.Success(data = europeCountry.subList(10,20)+asiaCountry.subList(10,20)))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getHardQuizFlagQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItem() }
            val asiaCountry=countryApi.getCountryWithRegion("Asia").map { it.toQuizItem() }
            val americaCountry=countryApi.getCountryWithRegion("America").map { it.toQuizItem() }
            emit(Response.Success(data = europeCountry.subList(20,30)+asiaCountry.subList(20,30)+americaCountry.subList(20,30)))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getHardQuizCapitalQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItemCapital() }
            val asiaCountry=countryApi.getCountryWithRegion("Asia").map { it.toQuizItemCapital() }
            val americaCountry=countryApi.getCountryWithRegion("America").map { it.toQuizItemCapital() }
            emit(Response.Success(data = europeCountry.subList(20,30)+asiaCountry.subList(20,30)+americaCountry.subList(20,30)))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getHardQuizEmblemsQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItemEmblems() }
                .take(30)
                .mapNotNull { quizItem ->
                    if (quizItem.flag.isNullOrEmpty()) {
                        countryApi.getCountryWithRegion("Europe").take(1).map { it.toQuizItemEmblems() }.let { newFlag ->
                            newFlag.map {
                                quizItem.copy(flag = it.flag)
                            }
                        }
                    } else {
                        listOf(quizItem)
                    }
                }.flatten()

            val asiaCountry = countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItemEmblems() }
                .take(30)
                .map { quizItem ->
                    if (quizItem.flag.isNullOrEmpty()) {
                        countryApi.getCountryWithRegion("Asia").take(1).map { it.toQuizItemEmblems() }.let { newFlag ->
                            newFlag.map {
                                quizItem.copy(flag = it.flag)
                            }
                        }
                    } else {
                        listOf(quizItem)
                    }
                }.flatten()

            val americaCountry = countryApi.getCountryWithRegion("America")
                .map { it.toQuizItemEmblems() }
                .take(30)
                .map { quizItem ->
                    if (quizItem.flag.isNullOrEmpty()) {
                        countryApi.getCountryWithRegion("America").take(1).map { it.toQuizItemEmblems() }.let { newFlag ->
                            newFlag.map {
                                quizItem.copy(flag = it.flag)
                            }
                        }
                    } else {
                        listOf(quizItem)
                    }
                }.flatten()

            emit(Response.Success(data = europeCountry.subList(20,30)+asiaCountry.subList(20,30)+americaCountry.subList(20,30)))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getExpertQuizFlagQuestion(): Flow<Response<List<QuizItem>>>  = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItem() }
            val asiaCountry=countryApi.getCountryWithRegion("Asia").map { it.toQuizItem() }
            val americaCountry=countryApi.getCountryWithRegion("America").map { it.toQuizItem() }
            val africaCountry=countryApi.getCountryWithRegion("Africa").map { it.toQuizItem() }
            emit(Response.Success(data = europeCountry.subList(30,40)+asiaCountry.subList(30,40)+americaCountry.subList(30,40)+africaCountry.subList(0,10)))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getExpertQuizCapitalQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItemCapital() }
            val asiaCountry=countryApi.getCountryWithRegion("Asia").map { it.toQuizItemCapital() }
            val americaCountry=countryApi.getCountryWithRegion("America").map { it.toQuizItemCapital() }
            val africaCountry=countryApi.getCountryWithRegion("Africa").map { it.toQuizItem() }
            emit(Response.Success(data = europeCountry.subList(30,40)+asiaCountry.subList(30,40)+americaCountry.subList(30,40)+africaCountry.subList(0,10)))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getExpertQuizEmblemsQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry = countryApi.getCountryWithRegion("Europe")
                .map { it.toQuizItemEmblems() }
                .take(40)
                .mapNotNull { quizItem ->
                    if (quizItem.flag.isNullOrEmpty()) {
                        countryApi.getCountryWithRegion("Europe").take(1).map { it.toQuizItemEmblems() }.let { newFlag ->
                            newFlag.map {
                                quizItem.copy(flag = it.flag)
                            }
                        }
                    } else {
                        listOf(quizItem)
                    }
                }.flatten()

            val asiaCountry = countryApi.getCountryWithRegion("Asia")
                .map { it.toQuizItemEmblems() }
                .take(40)
                .map { quizItem ->
                    if (quizItem.flag.isNullOrEmpty()) {
                        countryApi.getCountryWithRegion("Asia").take(1).map { it.toQuizItemEmblems() }.let { newFlag ->
                            newFlag.map {
                                quizItem.copy(flag = it.flag)
                            }
                        }
                    } else {
                        listOf(quizItem)
                    }
                }.flatten()

            val americaCountry = countryApi.getCountryWithRegion("America")
                .map { it.toQuizItemEmblems() }
                .take(40)
                .map { quizItem ->
                    if (quizItem.flag.isNullOrEmpty()) {
                        countryApi.getCountryWithRegion("America").take(1).map { it.toQuizItemEmblems() }.let { newFlag ->
                            newFlag.map {
                                quizItem.copy(flag = it.flag)
                            }
                        }
                    } else {
                        listOf(quizItem)
                    }
                }.flatten()

            val africaCountry = countryApi.getCountryWithRegion("Africa")
                .map { it.toQuizItemEmblems() }
                .take(20)
                .map { quizItem ->
                    if (quizItem.flag.isNullOrEmpty()) {
                        countryApi.getCountryWithRegion("Africa").take(1).map { it.toQuizItemEmblems() }.let { newFlag ->
                            newFlag.map {
                                quizItem.copy(flag = it.flag)
                            }
                        }
                    } else {
                        listOf(quizItem)
                    }
                }.flatten()
            emit(Response.Success(data = europeCountry.subList(30,40)+asiaCountry.subList(30,40)+americaCountry.subList(30,40)+africaCountry.subList(0,10)))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getEuropeCountryQuizQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItem() }
            emit(Response.Success(data = europeCountry))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getAmericaCountryQuizQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val americaCountry=countryApi.getCountryWithRegion("America").map { it.toQuizItem() }
            emit(Response.Success(data = americaCountry))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getAfricaCountryQuizQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val africaCountry=countryApi.getCountryWithRegion("Africa").map { it.toQuizItem() }
            emit(Response.Success(data = africaCountry))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getAsiaCountryQuizQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val africaCountry=countryApi.getCountryWithRegion("Asia").map { it.toQuizItem() }
            emit(Response.Success(data = africaCountry))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: java.lang.Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }

    override suspend fun getOceaniaCountryQuizQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val oceaniaCountry=countryApi.getCountryWithRegion("Oceania").map { it.toQuizItem() }
            emit(Response.Success(data = oceaniaCountry))
        }catch (e: SocketTimeoutException) {
            emit(Response.Error("Timeout.Try Again"))
        } catch (e: HttpException) {
            emit(Response.Error("Check your internet connection.."))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }catch (e: Exception) {
            emit(Response.Error("An unexpected error occured"))
        }
    }
}