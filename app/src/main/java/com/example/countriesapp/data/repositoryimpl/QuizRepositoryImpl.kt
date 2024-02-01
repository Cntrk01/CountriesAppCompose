package com.example.countriesapp.data.repositoryimpl

import com.example.countriesapp.data.remote.CountryApi
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.data.response.toQuizItem
import com.example.countriesapp.data.response.toQuizItemCapital
import com.example.countriesapp.data.response.toQuizItemEmblems
import com.example.countriesapp.domain.model.QuizItem
import com.example.countriesapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(private val countryApi: CountryApi) : QuizRepository {

    override suspend fun getEasyQuizFlagQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItem() }
            emit(Response.Success(data = europeCountry.subList(0,10)))
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }
    }

    override suspend fun getEasyQuizCapitalQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItemCapital() }
            emit(Response.Success(data = europeCountry.subList(0,10)))
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
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
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }
    }

    override suspend fun getMediumQuizFlagQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItem() }
            val asiaCountry=countryApi.getCountryWithRegion("Asia").map { it.toQuizItem() }
            emit(Response.Success(data = europeCountry.subList(10,20)+asiaCountry.subList(10,20)))
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }
    }

    override suspend fun getMediumQuizCapitalQuestion(): Flow<Response<List<QuizItem>>>  = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItemCapital() }
            val asiaCountry=countryApi.getCountryWithRegion("Asia").map { it.toQuizItemCapital() }
            emit(Response.Success(data = europeCountry.subList(10,20)+asiaCountry.subList(10,20)))
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
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
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }
    }

    override suspend fun getHardQuizFlagQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItem() }
            val asiaCountry=countryApi.getCountryWithRegion("Asia").map { it.toQuizItem() }
            val americaCountry=countryApi.getCountryWithRegion("America").map { it.toQuizItem() }
            emit(Response.Success(data = europeCountry.subList(20,30)+asiaCountry.subList(20,30)+americaCountry.subList(20,30)))
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }
    }

    override suspend fun getHardQuizCapitalQuestion(): Flow<Response<List<QuizItem>>> = flow {
        try {
            emit(Response.Loading())
            val europeCountry=countryApi.getCountryWithRegion("Europe").map { it.toQuizItemCapital() }
            val asiaCountry=countryApi.getCountryWithRegion("Asia").map { it.toQuizItemCapital() }
            val americaCountry=countryApi.getCountryWithRegion("America").map { it.toQuizItemCapital() }
            emit(Response.Success(data = europeCountry.subList(20,30)+asiaCountry.subList(20,30)+americaCountry.subList(20,30)))
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
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
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
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
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
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
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
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
        }catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Response.Error("Couldn't reach server.Check your internet connection.."))
        }
    }

}