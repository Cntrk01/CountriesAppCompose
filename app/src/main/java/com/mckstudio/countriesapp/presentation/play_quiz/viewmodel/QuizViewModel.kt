package com.example.countriesapp.presentation.play_quiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.domain.use_case.QuizUseCase
import com.example.countriesapp.presentation.play_quiz.state.QuizState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val quizUseCase: QuizUseCase) : ViewModel() {

    private val _state = MutableStateFlow(QuizState())
    val quizState: StateFlow<QuizState> = _state

    fun getEasyQuizFlagQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getEasyQuizCapitalQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase.getOceaniaCountryQuizQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getEasyQuizEmblemsQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase.getEasyQuizEmblemsQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getMediumQuizFlagQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase.getMediumQuizFlagQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getMediumQuizCapitalQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase.getMediumQuizCapitalQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getMediumQuizEmblemsQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase.getMediumQuizEmblemsQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getHardQuizFlagQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase.getHardQuizFlagQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getHardQuizCapitalQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase.getHardQuizCapitalQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getHardQuizEmblemsQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase.getHardQuizEmblemsQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getExpertQuizFlagQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase.getExpertQuizFlagQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getExpertQuizCapitalQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase.getExpertQuizCapitalQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getExpertQuizEmblemsQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase.getExpertQuizEmblemsQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getEuropeCountryQuizQuestion() = viewModelScope.launch(Dispatchers.IO) {
        quizUseCase.getEuropeCountryQuizQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }
    fun getAmericaCountryQuizQuestion() = viewModelScope.launch(Dispatchers.IO){
        quizUseCase.getAmericaCountryQuizQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getAfricaCountryQuizQuestion() = viewModelScope.launch(Dispatchers.IO){
        quizUseCase.getAfricaCountryQuizQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getAsiaCountryQuizQuestion() = viewModelScope.launch(Dispatchers.IO){
        quizUseCase.getAsiaCountryQuizQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun getOceaniaCountryQuizQuestion() = viewModelScope.launch(Dispatchers.IO){
        quizUseCase.getOceaniaCountryQuizQuestion().collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            error =response.message.toString(),
                            loading = false
                        )
                    }
                }

                else -> {
                    _state.update {
                        it.copy(
                            error = "",
                            loading = false,
                            quizData = response.data
                        )
                    }
                }
            }
        }
    }

    fun resetState(){
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