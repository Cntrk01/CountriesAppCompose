package com.mckstudio.countriesapp.presentation.play_quiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.common.Constants
import com.mckstudio.countriesapp.domain.model.QuizItem
import com.mckstudio.countriesapp.domain.repository.QuizRepository
import com.mckstudio.countriesapp.presentation.play_quiz.state.QuizState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//YAPACAĞIM.
enum class QuizLevel { EASY, MEDIUM, HARD, EXPERT }
enum class QuizType { FLAG, CAPITAL, EMBLEMS }
enum class Region { EUROPE, AMERICA, AFRICA, ASIA, OCEANIA }

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(QuizState())
    val quizState: StateFlow<QuizState> = _state

    private val quizMap: Map<Pair<String, String>, () -> Flow<Response<List<QuizItem>>>> = mapOf(
        Constants.EASY to mapOf(
            Constants.Flag to { quizRepository.getEasyQuizFlagQuestion() },
            Constants.Capital to { quizRepository.getEasyQuizCapitalQuestion() },
            Constants.Emblems to { quizRepository.getEasyQuizEmblemsQuestion() }
        ),
        Constants.MEDIUM to mapOf(
            Constants.Flag to { quizRepository.getMediumQuizFlagQuestion() },
            Constants.Capital to { quizRepository.getMediumQuizCapitalQuestion() },
            Constants.Emblems to { quizRepository.getMediumQuizEmblemsQuestion() }
        ),
        Constants.HARD to mapOf(
            Constants.Flag to { quizRepository.getHardQuizFlagQuestion() },
            Constants.Capital to { quizRepository.getHardQuizCapitalQuestion() },
            Constants.Emblems to { quizRepository.getHardQuizEmblemsQuestion() }
        ),
        Constants.EXPERT to mapOf(
            Constants.Flag to { quizRepository.getExpertQuizFlagQuestion() },
            Constants.Capital to { quizRepository.getExpertQuizCapitalQuestion() },
            Constants.Emblems to { quizRepository.getExpertQuizEmblemsQuestion() }
        )
    ).flatMap { (level, typeMap) ->
        typeMap.map { (type, func) -> level to type to func }
    }.toMap()

    private val regionMap: Map<String, () -> Flow<Response<List<QuizItem>>>> = mapOf(
        Constants.EUROPE to { quizRepository.getEuropeCountryQuizQuestion() },
        Constants.AMERICA to { quizRepository.getAmericaCountryQuizQuestion() },
        Constants.AFRICA to { quizRepository.getAfricaCountryQuizQuestion() },
        Constants.ASIA to { quizRepository.getAsiaCountryQuizQuestion() },
        Constants.OCEANIA to { quizRepository.getOceaniaCountryQuizQuestion() }
    )

    fun getQuiz(difficulty: String, type: String) {
        val flow = quizMap[difficulty to type]?.invoke()
            ?: regionMap[difficulty]?.invoke()

        flow?.reduceQuizState()
    }

    private fun Flow<Response<List<QuizItem>>>.reduceQuizState() {
        viewModelScope.launch(Dispatchers.IO) {
            collectLatest { response ->
                when (response) {
                    is Response.Loading -> _state.update {
                        it.copy(loading = true, error = "")
                    }
                    is Response.Error -> _state.update {
                        it.copy(
                            loading = false,
                            error = response.message
                        )
                    }

                    is Response.Success -> _state.update {
                        it.copy(
                            loading = false,
                            error = "",
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    private fun resetState() {
        _state.update {
            it.copy(
                loading = false,
                error = "",
                quizData = emptyList()
            )
        }
    }

    override fun onCleared() {
        resetState()
        super.onCleared()
    }
}